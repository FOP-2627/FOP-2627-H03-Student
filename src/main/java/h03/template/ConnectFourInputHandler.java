package h03.template;

import fopbot.RobotFamily;
import fopbot.World;
import h03.ConnectFour;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/**
 * The {@link ConnectFourInputHandler} handles the input of the users.
 */
@DoNotTouch
public class ConnectFourInputHandler {
    /**
     * The input queue.
     */
    private final BlockingDeque<Integer> inputQueue = new LinkedBlockingDeque<>();

    /**
     * The {@link ConnectFour} instance.
     */
    private final ConnectFour connectFour;

    /**
     * Whether the row select mode is active.
     */
    private final AtomicBoolean rowSelectMode = new AtomicBoolean(false);

    /**
     * Whether the game has finished.
     */
    private final AtomicBoolean gameHasFinished = new AtomicBoolean(false);

    /**
     * The status label.
     */
    private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);

    /**
     * Creates a new {@link ConnectFourInputHandler} instance.
     *
     * @param connectFour the {@link ConnectFour} instance
     */
    public ConnectFourInputHandler(final ConnectFour connectFour) {
        this.connectFour = connectFour;
        final int padding = 4; // Padding in pixels
        statusLabel.setBorder(new EmptyBorder(padding, padding, padding, padding));
    }

    /**
     * Sets the color of the given column to the given color.
     *
     * @param column        the column to set the color of
     * @param colorSupplier the color to set
     */
    private void setColumnColor(final int column, final Supplier<Color> colorSupplier) {
        for (int i = 0; i < World.getHeight(); i++) {
            final int finalI = i;
            SwingUtilities.invokeLater(() -> World.getGlobalWorld().getField(column, finalI).setFieldColor(colorSupplier));
        }
    }

    /**
     * Executes the given action only if the game is running.
     *
     * @param action the action to execute
     */
    private void whenGameIsRunning(final Runnable action) {
        if (connectFour.isRunning()) action.run();
    }

    /**
     * Installs the input handler to the fopbot world.
     */
    @SuppressWarnings("UnstableApiUsage")
    public void install() {
        final var guiPanel = World.getGlobalWorld().getGuiPanel();
        final var guiFrame = World.getGlobalWorld().getGuiFrame();
        World.getGlobalWorld().getInputHandler().addFieldClickListener(
            e -> whenGameIsRunning(() -> addInput(e.getField().getX()))
        );
        World.getGlobalWorld().getInputHandler().addFieldHoverListener(e -> whenGameIsRunning(() -> {
            // deselect last hovered field, if any
            if (e.getPreviousField() != null && !gameHasFinished.get()) {
                setColumnColor(e.getPreviousField().getX(), () -> null);
            }
            if (rowSelectMode.get()) {
                // select current hovered field
                if (e.getField() != null) {
                    setColumnColor(
                        e.getField().getX(),
                        () -> guiPanel.isDarkMode()
                            ? Color.yellow
                            : Color.orange
                    );
                }
            }
        }));
        statusLabel.setFont(statusLabel.getFont().deriveFont(guiPanel.scale(20.0f)));
        guiFrame.add(statusLabel, BorderLayout.NORTH);
        guiFrame.pack();
        guiPanel.addDarkModeChangeListener(this::onDarkModeChange);
        guiPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                statusLabel.setFont(
                    statusLabel.getFont().deriveFont(
                        Math.max(20f, 0.04f * Math.min(guiPanel.getWidth(), guiPanel.getHeight()))
                    )
                );
            }
        });
        // trigger dark mode change to set the correct color
        guiPanel.setDarkMode(World.getGlobalWorld().getGuiPanel().isDarkMode());
    }

    /**
     * Called when the dark mode changes.
     *
     * @param e the property change event
     */
    @SuppressWarnings("UnstableApiUsage")
    public void onDarkModeChange(final PropertyChangeEvent e) {
        final var darkMode = (boolean) e.getNewValue();
        statusLabel.setForeground(darkMode ? Color.white : Color.black);
        World.getGlobalWorld().getGuiFrame().getContentPane().setBackground(darkMode ? Color.black : Color.white);
    }

    /**
     * Adds an input to the input queue. When {@link #getNextInput(RobotFamily[][], RobotFamily)} is called, the program
     * will wait until this method is called.
     *
     * @param input the input to add
     */
    public void addInput(final int input) {
        inputQueue.add(input);
    }

    /**
     * Returns the next input from the input queue. If the input is invalid, the user will be prompted to enter a new
     * input. The program will halt until a valid input is entered. If the gameboard is already full
     * ({@link ConnectFour#isGameBoardFull(RobotFamily[][])}), {@code -1} is returned.
     *
     * @param stones        the current state of the game board
     * @param currentPlayer the current player
     * @return the next input from the input queue (or {@code -1} when full)
     */
    public int getNextInput(final RobotFamily[][] stones, final RobotFamily currentPlayer) {
        rowSelectMode.set(true);
        statusLabel.setText(
            "<html>Click on a column to insert a stone.<br>Current Player: %s</html>".formatted(currentPlayer.getName())
        );

        if (ConnectFour.testWinConditions(stones, currentPlayer)) {
            rowSelectMode.set(false);
            displayWinnerStatus(currentPlayer);
        } else if (ConnectFour.isGameBoardFull(stones)) {
            rowSelectMode.set(false);
            displayDrawStatus();
            return -1;
        }

        try {
            final int input = inputQueue.take();
            System.out.println("Received column input: " + input);
            if (!ConnectFour.validateInput(stones, input)) {
                System.out.println("Invalid column input, please try again.");
                return getNextInput(stones, currentPlayer);
            }
            rowSelectMode.set(false);
            return input;
        } catch (final InterruptedException e) {
            rowSelectMode.set(false);
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets a status message, saying that the game has ended in a draw.
     */
    public void displayDrawStatus() {
        final String text = "<html>No valid columns found. <br>Hence, game ends with a draw.</i></html>";
        statusLabel.setText(text);
    }

    /**
     * Sets a status message, saying that the game has ended with a winner.
     *
     * @param winner the winner of the game
     */
    public void displayWinnerStatus(final RobotFamily winner) {
        final String text = "<html>Player %s has won the game!</html>";
        statusLabel.setText(text.formatted(winner.getName()));
    }

    /**
     * Waits for the user to click to close the game. The current status message is preserved
     * and supplemented with "(Click to close the game)" text.
     */
    public void waitForCloseInput() {
        waitForCloseInput(true);
    }

    /**
     * Waits for the user to click to close the game. Updates the status label to inform the user
     * that they can click to close the game window.
     *
     * @param keepStatus if {@code true}, the current status text is preserved and the close prompt
     *                   is appended to it; if {@code false}, the status label is replaced with only
     *                   the close prompt
     */
    public void waitForCloseInput(final boolean keepStatus) {
        rowSelectMode.set(false);
        gameHasFinished.set(true);

        // update status label
        String closeText = "<i>(Click to close the game)</i>";

        if (keepStatus) {
            closeText = "<html>" + closeText + "</html>";
        } else {
            String statusText = statusLabel.getText();
            final String appendText = "<br>" + closeText + "</html>";
            if (statusText.endsWith("</html>")) {
                closeText = statusText.substring(0, statusText.length() - "</html>".length()) + appendText;
            } else {
                closeText = "<html>" + statusText + appendText;
            }
        }
        statusLabel.setText(closeText);

        // wait for next click
        try {
            inputQueue.take();
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the {@link #statusLabel} of this {@link ConnectFourInputHandler}.
     *
     * <p>Use the {@link JLabel#getText()} method to get the current text of the label, and the
     * {@link JLabel#setText(String)} method to update the text.
     *
     * @return the {@link #statusLabel} of this {@link ConnectFourInputHandler}
     */
    public JLabel getStatusLabel() {
        return statusLabel;
    }
}

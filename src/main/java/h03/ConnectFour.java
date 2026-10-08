package h03;

import fopbot.RobotFamily;
import fopbot.World;
import h03.template.ConnectFourInputHandler;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * The {@link ConnectFour} class represents the main class of the FourWins game.
 */
@SuppressWarnings({"ManualArrayCopy", "ForLoopReplaceableByForEach"})
public class ConnectFour {
    /**
     * The width of the game board.
     */
    private final int width;
    /**
     * The height of the game board.
     */
    private final int height;
    /**
     * Indicates whether the game is currently running.
     */
    private boolean isRunning = false;

    /**
     * Whether the game should be visible.
     */
    private final boolean visible;

    /**
     * Delay of the world. Increase/Decrease this value to slow down/speed up the game.
     */
    private final int worldDelay;

    /**
     * The input handler for this exercise. It checks and waits for the next click in the game window.
     * (You do not need to understand its it inner workings)
     */
    private final ConnectFourInputHandler inputHandler = new ConnectFourInputHandler(this);

    /**
     * Creates a new {@link ConnectFour} instance with the given width and height.
     *
     * @param width  the width of the game board
     * @param height the height of the game board
     */
    ConnectFour(int width, int height) {
        this(width, height, true, 10);
    }

    /**
     * Creates a new {@link ConnectFour} instance with the given width and height.
     *
     * @param width      the width of the game board
     * @param height     the height of the game board
     * @param visible    whether the world should be visible
     * @param worldDelay delay of operations on the world
     */
    ConnectFour(int width, int height, boolean visible, int worldDelay) {
        this.width = width;
        this.height = height;
        this.visible = visible;
        this.worldDelay = worldDelay;
    }

    /**
     * Validates if a given column can accept a new stone. The column is valid if it is within bounds and
     * the top cell of the column is empty (not fully occupied).
     *
     * @param stones 2D array representing the game board, where each cell contains a RobotFamily color indicating the
     *               player that has placed a stone in that position.
     * @param column The column index to validate.
     * @return true if the column is within bounds and has at least one unoccupied cell at the top; false otherwise.
     */
    @StudentImplementationRequired("H3.2.1")
    public static boolean validateInput(RobotFamily[][] stones, int column) {
        // TODO: H3.2.1
        return org.tudalgo.algoutils.student.Student.crash("H3.2.1 - Remove if implemented");
    }

    /**
     * Calculates the number of steps needed for a stone to fall in the specified column.
     * This represents how far down the stone needs to move to reach the first unoccupied slot.
     *
     * @param stones 2D array representing the game board, where each cell contains a RobotFamily object indicating the
     *               player that has placed a stone in that position.
     * @param column The column index where the stone is to be dropped.
     * @return The number of steps the stone needs to fall. Returns -1 if the column is full.
     */
    @StudentImplementationRequired("H3.2.2")
    public static int getNumberOfSteps(RobotFamily[][] stones, int column) {
        // TODO: H3.2.2
        return org.tudalgo.algoutils.student.Student.crash("H3.2.2 - Remove if implemented");
    }

    /**
     * Drops a stone into the specified column of the game board, simulating a falling animation. This method gets the
     * number of steps the stone needs to fall using the {@code getNumberOfSteps} method. It creates a new Robot
     * instance to represent the stone with the currentPlayer's {@link RobotFamily} in the given column at the top row.
     * After that it simulates the stone's fall by moving it down until it reaches its destination row. Once
     * the stone reaches its destination, the method updates the stones array (a 2D array of {@link RobotFamily} colors) to
     * mark the slot as occupied by the currentPlayer.
     *
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily object
     *                      indicating the player that has placed a stone in that position.
     * @param column        The column index where the stone is to be dropped.
     * @param currentPlayer The RobotFamily object representing the current player dropping the stone.
     */
    @StudentImplementationRequired("H3.2.2")
    public static void dropStone(RobotFamily[][] stones, int column, RobotFamily currentPlayer) {
        // TODO: H3.2.2
        org.tudalgo.algoutils.student.Student.crash("H3.2.2 - Remove if implemented");
    }

    /**
     * Checks if all elements in the given line array represent stones of the current player.
     *
     * @param line          An array of {@link RobotFamily} values representing a line of the game board.
     * @param currentPlayer The {@link RobotFamily} color representing the current player to check for.
     * @return true if all elements in the line match the currentPlayer; false otherwise.
     */
    @StudentImplementationRequired("H3.2.3")
    public static boolean testAllInLine(RobotFamily[] line, RobotFamily currentPlayer) {
        // TODO: H3.2.3
        return org.tudalgo.algoutils.student.Student.crash("H3.2.3 - Remove if implemented");
    }

    /**
     * Checks if the current player has won by forming a horizontal line of at least four consecutive stones.
     *
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily color
     *                      indicating the player that has placed a stone in that position.
     * @param currentPlayer The RobotFamily color representing the current player to check for a win.
     * @return true if the current player has formed a horizontal line of at least four consecutive stones; false otherwise.
     */
    @StudentImplementationRequired("H3.2.3")
    public static boolean testWinHorizontal(RobotFamily[][] stones, RobotFamily currentPlayer) {
        // TODO: H3.2.3
        return org.tudalgo.algoutils.student.Student.crash("H3.2.3 - Remove if implemented");
    }

    /**
     * Checks if the current player has won by forming a vertical line of at least four consecutive stones.
     *
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily color
     *                      indicating the player that has placed a stone in that position.
     * @param currentPlayer The RobotFamily color representing the current player to check for a win.
     * @return true if the current player has formed a vertical line of at least four consecutive stones; false otherwise.
     */
    @StudentImplementationRequired("H3.2.3")
    public static boolean testWinVertical(RobotFamily[][] stones, RobotFamily currentPlayer) {
        // TODO: H3.2.3
        return org.tudalgo.algoutils.student.Student.crash("H3.2.3 - Remove if implemented");
    }

    /**
     * Checks if the current player has won by forming a diagonal line of at least consecutive four stones.
     *
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily color
     *                      indicating the player that has placed a stone in that position.
     * @param currentPlayer The RobotFamily color representing the current player to check for a win.
     * @return true if the current player has formed a diagonal line of at least four stones; false otherwise.
     */
    @DoNotTouch
    public static boolean testWinDiagonal(RobotFamily[][] stones, RobotFamily currentPlayer) {
        final int MAX_STONES = 4;

        final int WIDTH = World.getWidth();
        final int HEIGHT = World.getHeight();
        int[] direction = new int[]{1, 1};

        // for every field
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                // for every direction
                for (int nthDirection = 0; nthDirection < 4; nthDirection++) {
                    final int[] pos = {x, y};

                    // test for consecutive coins
                    int coinCount = 0; // start counting at 0
                    while (pos[0] >= 0 && pos[0] < WIDTH && pos[1] >= 0 && pos[1] < HEIGHT && stones[pos[1]][pos[0]] == currentPlayer) {
                        coinCount++; // count every stone that has currentPlayer's color
                        if (coinCount >= MAX_STONES) {
                            return true;
                        }
                        pos[0] += direction[0];
                        pos[1] += direction[1];
                    }

                    direction = new int[]{direction[1], -direction[0]}; // next direction (rotate by 90 deg)
                }
            }
        }

        return false;
    }

    /**
     * Checks if the current player has won by any condition. The conditions can be a horizontal, vertical, or
     * diagonal line of at least four consecutive stones.
     *
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily color
     *                      indicating the player that has placed a stone in that position.
     * @param currentPlayer The RobotFamily color representing the current player to check for a win.
     * @return true if the current player has formed a line of at least four stones in any direction; false otherwise.
     */
    @StudentImplementationRequired("H3.2.3")
    public static boolean testWinConditions(RobotFamily[][] stones, RobotFamily currentPlayer) {
        // TODO: H3.2.3
        return org.tudalgo.algoutils.student.Student.crash("H3.2.3 - Remove if implemented");
    }

    /**
     * Switches the player for each turn. If the current player is SQUARE_BLUE, SQUARE_RED is returned as the next
     * player. If the current player is SQUARE_RED, SQUARE_BLUE is returned as the next player.
     *
     * @param currentPlayer The player color of the current player.
     * @return The player color of the next player.
     */
    @StudentImplementationRequired("H3.2.4")
    public static RobotFamily nextPlayer(RobotFamily currentPlayer) {
        // TODO: H3.2.4
        return org.tudalgo.algoutils.student.Student.crash("H3.2.4 - Remove if implemented");
    }

    /**
     * Displays the winner of the game by filling the entire game board with the color that is representing the winner.
     *
     * @param winner The {@link RobotFamily} color of the winner.
     */
    @StudentImplementationRequired("H3.2.4")
    public static void colorBackground(RobotFamily winner) {
        // TODO: H3.2.4
        org.tudalgo.algoutils.student.Student.crash("H3.2.4 - Remove if implemented");
    }

    /**
     * Displays a Message in the console indicating the game is drawn.
     */
    @StudentImplementationRequired("H3.2.4")
    public void writeDrawMessage() {
        // TODO: H3.2.4
        org.tudalgo.algoutils.student.Student.crash("H3.2.4 - Remove if implemented");
    }

    /**
     * Displays a Message in the console indicating the game is won by a player.
     *
     * @param winner {@link RobotFamily} of the winning player
     */
    @StudentImplementationRequired("H3.2.4")
    public void writeWinnerMessage(RobotFamily winner) {
        // TODO: H3.2.4
        org.tudalgo.algoutils.student.Student.crash("H3.2.4 - Remove if implemented");
    }

    /**
     * Checks if all columns of the game board are fully occupied.
     *
     * @param stones 2D array representing the game board, where each cell contains a RobotFamily
     * @return true if all columns of the game board are fully occupied; false otherwise.
     */
    @StudentImplementationRequired("H3.2.4")
    public static boolean isGameBoardFull(RobotFamily[][] stones) {
        // TODO: H3.2.4
        return org.tudalgo.algoutils.student.Student.crash("H3.2.4 - Remove if implemented");
    }

    /**
     * Executes the main game loop, handling player turns, stone drops, and win condition checks. This method
     * initializes the game board as a 2D array of RobotFamily colors, representing the slots that can be filled with
     * players' stones. It starts with SQUARE_BLUE as the current player (note: {@code nextPlayer()} is called first,
     * so SQUARE_RED actually goes first). Each iteration of the loop gets player input for a column to drop a stone,
     * switches the current player, drops the stone in the selected column, and checks for win conditions. If a win
     * condition is met or the board is full, the loop ends and the result is displayed.
     */
    @StudentImplementationRequired("H3.2.4")
    void gameLoop() {
        // TODO: H3.2.4
        org.tudalgo.algoutils.student.Student.crash("H3.2.4 - Remove if implemented");
    }

    /**
     * Starts the game by setting up the world and executing the game loop.
     */
    @DoNotTouch
    void startGame() {
        setupWorld();
        gameLoop();
    }

    /**
     * Stops the game by closing the world and window
     */
    @DoNotTouch
    void stopGame() {
        inputHandler.waitForCloseInput();
        isRunning = false;
        World.setVisible(false);
    }

    /**
     * Sets up the world and installs the {@link ConnectFourInputHandler}.
     */
    @DoNotTouch
    void setupWorld() {
        World.setSize(width, height);
        World.setDelay(worldDelay);
        World.setVisible(visible);
        inputHandler.install();
        isRunning = true;
    }

    /**
     * Sets the background color of a field at the specified coordinates. The color is derived from the
     * {@link RobotFamily} parameter, such as SQUARE_BLUE or SQUARE_RED.
     *
     * @param x     the x coordinate of the field
     * @param y     the y coordinate of the field
     * @param color the {@link RobotFamily} corresponding to the field color to set
     */
    @DoNotTouch
    public static void setFieldColor(int x, int y, RobotFamily color) {
        World.getGlobalWorld().setFieldColor(x, y, color.getColor());
    }

    /**
     * Waits for the next input from the user, which is expected to be a column index where the current player wants
     * to drop a stone.
     *
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily color
     *                      indicating the player that has placed a stone in that position.
     * @param currentPlayer The RobotFamily color representing the current player who is about to make a move.
     * @return The column index input by the user for dropping a stone.
     * @throws NullPointerException if stones or currentPlayer is null when calling this method.
     */
    @DoNotTouch
    public int waitForNextInput(RobotFamily[][] stones, RobotFamily currentPlayer) {
        if (stones == null || currentPlayer == null) {
            throw new NullPointerException("stones and/or currentPlayer cannot be null when calling waitForNextInput");
        }
        return inputHandler.getNextInput(stones, currentPlayer);
    }

    /**
     * Returns this instance's {@link ConnectFourInputHandler}.
     *
     * @return the input handler
     */
    @DoNotTouch
    public ConnectFourInputHandler getInputHandler() {
        return inputHandler;
    }

    /**
     * Returns {@code true} when the game is finished, {@code false} otherwise.
     *
     * @return whether the game is finished.
     */
    @DoNotTouch
    public boolean isRunning() {
        return isRunning;
    }

}

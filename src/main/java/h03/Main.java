package h03;

import fopbot.RobotFamily;
import fopbot.World;

import java.util.Arrays;

import static org.tudalgo.algoutils.student.test.StudentTestUtils.testEquals;

/**
 * Main entry point in executing the program.
 */
@SuppressWarnings("unused")
public class Main {
    /**
     * Main entry point in executing the program.
     * <p>
     * This method runs all manual tests in sequence and then starts a full Connect Four game.
     * Tests should complete without throwing exceptions. Output will be printed to the console
     * for you to review and verify correctness.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(final String[] args) {
        // You can comment out any of these test methods to run only the tests you want.
        // For example, if you only want to test Tribonacci functions, comment out testH3_2(), and runConnectFour()
        // (Most IDEs have a "line comment" / "toggle line comment" shortcut which is very useful.
        // The default "Ctrl"+"/" in IntelliJ is a bit impractical.)

        // Test Tribonacci functions (push, calculateNextTribonacci, tribonacci)
        testH3_1();

        // Test individual Connect Four mechanics (drop one stone, see steps calculated)
        testH3_2();

        // Play a complete Connect Four game with both players
        runConnectFour();
    }

    /**
     * Manual test for H3.1 - Tribonacci number sequence functions.
     * <p>
     * Check the console output to ensure all three tests produce the expected results.
     * If a test fails, an assertion error will be printed indicating what went wrong.
     */
    public static void testH3_1() {
        // getStartingNumbers test
        final int[] startingNumbers = TribonacciNumbers.getStartingNumbers();
        System.out.println("Starting numbers: " + Arrays.toString(startingNumbers));
        testEquals(0, startingNumbers[0]);
        testEquals(1, startingNumbers[1]);
        testEquals(1, startingNumbers[2]);

        // push test
        final int[] pushInput = new int[]{3, 7, 13};
        final int[] pushOutput = TribonacciNumbers.push(pushInput, 42);
        System.out.println("Pushed '" + 42 + "' to '" + Arrays.toString(pushInput)
            + "' resulting in: '" + Arrays.toString(pushOutput) + "'");
        testEquals(42, pushOutput[pushOutput.length - 1]);


        // calculateNextTribonacci test
        final int[] tribonacci = new int[]{0, 1, 1, 2, 4};
        final int[] nextTribonacci = TribonacciNumbers.calculateNextTribonacci(tribonacci);
        System.out.println("Next Tribonacci for '" + Arrays.toString(tribonacci)
            + "' resulting in: '" + Arrays.toString(nextTribonacci) + "'");
        testEquals(7, nextTribonacci[nextTribonacci.length - 1]);

        // tribonacci test
        final int n = 10;
        final int nthTribonacci = TribonacciNumbers.tribonacci(n);
        System.out.println("The " + n + "-th Tribonacci number is: '" + nthTribonacci + "'");
        testEquals(149, TribonacciNumbers.tribonacci(10));
    }

    /**
     * Manual test for H3.2 - Connect Four basic game mechanics.
     * <p>
     * This test demonstrates the basic functionality of the Connect Four game without playing a full game.
     * It creates a 7x6 game board (standard Connect Four dimensions) and tests individual game mechanics.
     * <p>
     * After the test completes, the game window will close. This is not a full game,
     * just a test of individual functions. To play a full game, run {@code runConnectFour()}.
     */
    public static void testH3_2() {
        ConnectFour test = new ConnectFour(7, 6);
        test.setupWorld();

        final RobotFamily[][] stones = new RobotFamily[World.getHeight()][World.getWidth()];
        RobotFamily currentPlayer = RobotFamily.SQUARE_BLUE;

        final int column = test.getInputHandler().getNextInput(stones, currentPlayer);

        final int nSteps = ConnectFour.getNumberOfSteps(stones, column);
        System.out.println("Number of Steps: " + nSteps);

        ConnectFour.dropStone(stones, column, currentPlayer);

        currentPlayer = ConnectFour.nextPlayer(currentPlayer);
        System.out.println("Next player is: " + currentPlayer);

        test.stopGame();
    }

    /**
     * Runs a full Connect Four game for manual testing.
     * <p>
     * This method starts the complete, Connect Four game implementation of your {@link ConnectFour#gameLoop()} with
     * all game mechanics working together. You can play as both players by clicking on columns to drop stones.
     */
    public static void runConnectFour() {
        ConnectFour game = new ConnectFour(7, 6);
        game.startGame();
        game.stopGame();
    }

}

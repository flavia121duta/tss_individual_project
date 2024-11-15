import org.example.calculator.CalculatorDivisors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquivalencePartitioningTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testSolveWithMultipleValidInputsAndThenExit() {
        // start with n = 20 ---> s = y (valid inputs)
        // then n = 12 ---> s = n (valid inputs)
        String simulatedInput = "20\ny\n12\nn\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        CalculatorDivisors.solve();

        String programOutput = outputStream.toString();
        assertTrue(programOutput.contains("Enter a number between 1 and 100000: "));
        assertTrue(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 20] is 12"));
        assertTrue(programOutput.contains("Do you want to continue? Type y or n:"));
        assertTrue(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 12] is 12"));
        assertTrue(programOutput.contains("Program finished."));
    }

    @Test
    public void testSolveWithMultipleInvalidInputsAndThenExit() {
        // start with invalid n = -3
        // then enter invalid n = 200000
        // the program will keep asking for a valid n before asking for s
        // then n = 20 and s = 'n' ---> STOP
        String simulatedInput = "-3\n200000\n20\nn\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        CalculatorDivisors.solve();
        String programOutput = outputStream.toString();

        // print those messages twice
        assertTrue(programOutput.contains("Enter a number between 1 and 100000: "));

        // this message will be displayed for -3 and 200 000
        assertTrue(programOutput.contains("Invalid integer. Try again."));

        assertFalse(programOutput.contains(
                "The number with the maximum divisors in the interval [1, -3] is"));
        assertFalse(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 200000] is"));

        // after entering n=20
        assertTrue(programOutput.contains("Do you want to continue? Type y or n:"));
        assertTrue(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 20] is 12"));
        assertTrue(programOutput.contains("Program finished."));
    }


    @Test
    public void testSolveWithValidInputsAndThenExit() {
        // n = 6 ---> s = n
        String simulatedInput = "6\nn";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        CalculatorDivisors.solve();

        String programOutput = outputStream.toString();
        assertTrue(programOutput.contains("Enter a number between 1 and 100000: "));
        assertTrue(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 6] is 6"));
        assertTrue(programOutput.contains("Do you want to continue? Type y or n:"));
    }
}

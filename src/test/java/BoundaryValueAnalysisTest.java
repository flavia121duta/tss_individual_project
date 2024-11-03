import org.example.calculator.CalculatorDivisors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoundaryValueAnalysisTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testGetNumberWithMaximumDivisorsFromInterval() {
        // 1 is an edge case, with only one divisor, itself
        assertEquals(1, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(1));

        assertEquals(1680, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(2025));
        assertEquals(83160, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(100000));
    }
    @Test
    public void testBoundaryValueAnalysis() {
        /*
            The only valid values for n are numbers from {1, 2, ... 100 000}
         */
        // n = -2025 invalid input then program will keep asking the user for a valid input
        // n = 0
        // n = 100000 valid input, s = y
        // n = 100001 invalid input
        // n = 200000 invalid input
        // n = 1 valid input, s = y to continue the program
        // n = 2025 valid input, s = n to stop the program
        String simulatedInput = "-2025\n0\n100000\ny\n100001\n200000\n1\ny\n2025\nn";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        CalculatorDivisors.solve();

        String programOutput = outputStream.toString();

        assertTrue(programOutput.contains("Enter a number between 1 and 100000: "));

        // this message will be displayed for -2025, 0, 100 001 and 200 000
        assertTrue(programOutput.contains("Invalid integer. Try again."));

        assertFalse(programOutput.contains(
                "The number with the maximum divisors in the interval [1, -2025] is"));
        assertFalse(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 0] is"));
        assertFalse(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 100001] is"));
        assertFalse(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 200000] is"));

        assertTrue(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 100000] is 83160"));
        assertTrue(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 1] is 1"));
        assertTrue(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 2025] is 1680"));

        assertTrue(programOutput.contains("Do you want to continue? Type y or n:"));
        assertTrue(programOutput.contains("Program finished."));
    }
}

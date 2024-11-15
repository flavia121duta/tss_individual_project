import org.example.calculator.CalculatorDivisors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CauseEffectGraphingTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testGetNumberWithMaximumDivisorsFromInterval() {
        assertEquals(6, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(10));
        assertEquals(12, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(20));
    }
    @Test
    public void testCauseEffectGraphing() {
        // n = -10 invalid input then program will keep asking the user for a valid input
        // n = 20, s = y to continue the program
        // n = 100, s = n to stop the program

        String simulatedInput = "-10\n20\ny\n100\nn";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        CalculatorDivisors.solve();

        String programOutput = outputStream.toString();

        assertTrue(programOutput.contains("Enter a number between 1 and 100000: "));

        // this message will be displayed for n = -10
        assertTrue(programOutput.contains("Invalid integer. Try again."));

        assertFalse(programOutput.contains(
                "The number with the maximum divisors in the interval [1, -10] is"));

        assertTrue(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 20] is 12"));
        assertTrue(programOutput.contains(
                "The number with the maximum divisors in the interval [1, 100] is 60"));

        assertTrue(programOutput.contains("Do you want to continue? Type y or n:"));
        assertTrue(programOutput.contains("Program finished."));
    }
}

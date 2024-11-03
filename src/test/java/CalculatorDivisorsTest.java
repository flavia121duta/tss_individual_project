import org.example.calculator.CalculatorDivisors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorDivisorsTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    //------------------------------------------

    @Test
    public void testGetNumberOfDivisorsOf() {
        // 1 is an edge case, with only one divisor, itself
        assertEquals(1, CalculatorDivisors.getNumberOfDivisorsOf(1));

        // 2 is a special case, being the only even prime number
        assertEquals(2, CalculatorDivisors.getNumberOfDivisorsOf(2));

        // the divisors of 6 are 1, 2, 3, 6
        assertEquals(4, CalculatorDivisors.getNumberOfDivisorsOf(6));

        // 7 is prime number, so it has only 2 divisors: 1 and itself
        assertEquals(2, CalculatorDivisors.getNumberOfDivisorsOf(7));

        // 9 is a perfect square and its divisors are 1, 3, 9
        assertEquals(3, CalculatorDivisors.getNumberOfDivisorsOf(9));

        // D20 = {1, 2, 4, 5, 10, 20}
        assertEquals(6, CalculatorDivisors.getNumberOfDivisorsOf(20));

        // D12 = {1, 2, 3, 4, 6, 12}
        assertEquals(6, CalculatorDivisors.getNumberOfDivisorsOf(12));
    }


    @Test
    public void testGetNumberWithMaximumDivisorsFromInterval() {
        // edge case for n = 1
        assertEquals(1, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(1));

        // 6 is a number within the range [1, 10 000]
        // where 6 itself is the number with the most divisors from interval [1, 6]
        assertEquals(6, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(6));

        // number within the range [1, 10 000]
        // where not 10, nut 6 is the number with the most divisors from interval [1, 10]
        assertEquals(6, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(10));

        // exception n = 0
        assertEquals(-1, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(0));

        // exception for n = -5 (negative)
        assertEquals(-1, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(-3));

        // n = 20
        assertEquals(12, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(20));

        // n = 12
        assertEquals(12, CalculatorDivisors.getNumberWithMaximumDivisorsFromInterval(12));
    }

    @Test
    public void testSolveWithValidInputAndExit() {
        // valid input followed by 'n' (no)
        // n = 5, s = n
        String simulatedInput = "5\nn\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        CalculatorDivisors.solve();

        String programOutput = outputStream.toString();
        assertTrue(programOutput.contains("Enter a number between 1 and 100000: "));
        assertTrue(programOutput.contains("The number with the maximum divisors in the interval [1, 5] is"));
        assertTrue(programOutput.contains("Do you want to continue? Type y or n:"));
        assertTrue(programOutput.contains("Program finished."));
    }

    @Test
    public void testSolveWithInvalidInputThenValidInputAndExit() {
        // invalid string input, then valid input followed by 'n'
        String simulatedInput = "abc\n7\nn\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        CalculatorDivisors.solve();

        String programOutput = outputStream.toString();

        // n = 'abc'
        assertTrue(programOutput.contains("Enter a number between 1 and 100000: "));
        assertTrue(programOutput.contains("Invalid input. Please enter an integer."));

        // n = 7
        assertTrue(programOutput.contains("The number with the maximum divisors in the interval [1, 7] is"));
        assertTrue(programOutput.contains("Do you want to continue? Type y or n:")); // s = n
        assertTrue(programOutput.contains("Program finished."));
    }
}

package org.example.calculator;

import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Math.floor;

import static java.lang.Math.sqrt;

public class CalculatorDivisors {
    public static int getNumberOfDivisorsOf(int x) {
        if (x == 1 || x == 2) 
            return x;

        int numberOfDivisors = 2; // improper divisors (1 and the number itself)
        for (int d = 2; d <= sqrt(x); d++) {
            if (x % d == 0) 
                numberOfDivisors += 2;
        }
        if (sqrt(x) == floor(sqrt(x)))
            numberOfDivisors--;
        return numberOfDivisors;
    }

    public static int getNumberWithMaximumDivisorsFromInterval(int n) {
        int maxi = 0, foundNumber = -1;

        for (int x = 1; x <= n; x++) {
            int nr = getNumberOfDivisorsOf(x);
            if (nr > maxi) {
                maxi = nr;
                foundNumber = x;
            }
        }
        
        return foundNumber;
    }

    public static void solve() {
        Scanner console = new Scanner(System.in);
        boolean repeat = true;

        while (repeat) {
            int n = 0;
            boolean validInput = false;

            // handle invalid input for n when the user enters test instead of number
            while (!validInput) {
                try {
                    System.out.print("Enter a number between 1 and 100000: ");
                    n = console.nextInt();
                    if (n >= 1 && n <= 1e5) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid integer. Try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer."); // the input is not an integer
                    console.next(); // clear the invalid input from the console
                }
            }

            // calculate the number with maximum divisors in interval [1, n]
            int result = getNumberWithMaximumDivisorsFromInterval(n);
            System.out.println("The number with the maximum divisors in the interval [1, " + n + "] is " + result);

            // ask if the user wants to continue
            System.out.print("Do you want to continue? Type y or n: ");
            char s = console.next().toLowerCase().charAt(0);
            repeat = s == 'y';
        }

        System.out.println("Program finished.");
    }
}

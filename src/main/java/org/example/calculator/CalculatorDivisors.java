package org.example.calculator;

import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Math.floor;

import static java.lang.Math.sqrt;

public class CalculatorDivisors {
    public static int getNumberOfDivisorsOf(int x) {
        if (x == 1 || x == 2) 
            return x;

        int numberOfDivisors = 2; // divisors (1 and the number itself)
        for (int d = 2; d <= sqrt(x); d++) {
            if (x % d == 0) 
                numberOfDivisors += 2;
        }
        if (sqrt(x) == floor(sqrt(x)) 
            numberOfDivisors--;
        return numberOfDivisors;
    }

    public static int getNumberWithMaximumDivisorsFromInterval(int n) {
        int maxi = 0, foundNumber = 1;
        try {
            if (n < 1 || n > 1e5)
                throw new Exception("Error: Invalid number");
            for (int x = 1; x <= n; x++) {
                int nr = getNumberOfDivisorsOf(x);
                if (nr > maxi) {
                    maxi = nr;
                    foundNumber = x;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
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
                    System.out.print("n = ");
                    n = console.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                    console.next(); // Clear the invalid input
                }
            }

            // calculate the number with maximum divisors in interval [1, n]
            // and print if it exists
            int result = getNumberWithMaximumDivisorsFromInterval(n);
            if (result == -1) {
                // error message
                System.out.println("Try again.");
            } else {
                System.out.println("The number with the maximum divisors in the interval [1, " + n + "] is " + result);
            }

            // ask if the user wants to continue
            System.out.print("Do you want to continue? Type y or n: ");
            char s = console.next().toLowerCase().charAt(0);
            repeat = s == 'y';
        }

        System.out.println("Program finished.");
    }
}

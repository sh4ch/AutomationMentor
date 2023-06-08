package com.epam.auto.selenium01;

import org.testng.annotations.DataProvider;

public class CalculatorDataProvider extends CalculatorBaseTest {
    @DataProvider(name = "Calculator Data")
    public static Object[][] testDataProvider() {
        return new Object[][]{
                {3, 2},
                {5, 5},
                {45, 0},
                {1, -1}
        };
    }

    @DataProvider(name = "Zero Division Data")
    public static Object[][] divisionByZeroDataProvider() {
        return new Object[][]{
                {3, 0, new NumberFormatException("Attempt to divide by zero")},
                {3450L, 0, new NumberFormatException("Attempt to divide by zero")},
                {-3, 0, new NumberFormatException("Attempt to divide by zero")}
        };
    }

}

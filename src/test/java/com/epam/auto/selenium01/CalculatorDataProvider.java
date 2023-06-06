package com.epam.auto.selenium01;

import org.testng.annotations.DataProvider;

public class CalculatorDataProvider extends CalculatorBaseTest {
    @DataProvider(name = "Sum Data")
    public Object[][] sumDataProvider() {
        return new Object[][]{
                {3, 2, 5},
                {5, 5, 10},
                {120, 300, 420},
                {-6, -4, -10}
        };
    }

    @DataProvider(name = "Subtract Data")
    public Object[][] subtractDataProvider() {
        return new Object[][]{
                {3, 2, 1},
                {5, 5, 0},
                {120, 300, -180},
                {0, -3, 3}
        };
    }

    @DataProvider(name = "Multiply Data")
    public Object[][] multiplyDataProvider() {
        return new Object[][]{
                {3, 2, 6},
                {5, 5, 25},
                {0, 35, 0},
                {-3, 1, -3}
        };
    }

    @DataProvider(name = "Pow Data")
    public Object[][] powDataProvider() {
        return new Object[][]{
                {3, 2, 9.0},
                {5, 5, 3125.0},
                {45, 0, 1},
                {1, -1, 1.0}
        };
    }

}

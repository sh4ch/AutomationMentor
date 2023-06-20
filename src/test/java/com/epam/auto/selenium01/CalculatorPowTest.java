package com.epam.auto.selenium01;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class CalculatorPowTest extends CalculatorBaseTest {
    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Calculator Data")
    public void powTest(double a, double b) {
        assertThat(calculator.pow(a, b))
                .as("Calculator pow: %f ^ %f is not equal to expected", a, b)
                .isEqualTo(Math.pow(a, b));
    }
}

package com.epam.auto.selenium01;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class CalculatorPowTest extends CalculatorBaseTest {
    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Pow Data")
    public void powTest(double a, double b, double expected) {
        System.out.printf("subtract %f - %f data provider test\n", a, b);
        double actual = calculator.pow(a, b);
        assertThat(actual).isEqualTo(expected);
    }
}

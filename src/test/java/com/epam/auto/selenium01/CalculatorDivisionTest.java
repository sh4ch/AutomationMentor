package com.epam.auto.selenium01;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class CalculatorDivisionTest extends CalculatorBaseTest {
    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Zero Division Data")
    public void divisionByZeroTest(long a, long b, Object expected) {
        try {
            assertThat(calculator.div(a, b)).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Attempt to divide by zero");
        }
    }
}

package com.epam.auto.selenium01;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.testng.Assert.assertThrows;

import org.testng.annotations.Test;

public class CalculatorDivisionTest extends CalculatorBaseTest {
    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Zero Division Data")
    public void divisionByZeroTest(long a, long b) {
        assertThrows(NumberFormatException.class, () -> calculator.div(a, b));
    }
}

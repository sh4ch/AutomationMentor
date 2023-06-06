package com.epam.auto.selenium01;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class CalculatorSubtractTest extends CalculatorBaseTest {

    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Subtract Data")
    public void subtractTest(long a, long b, long expected) {
        System.out.printf("subtract %d - %d data provider test\n", a, b);
        long actual = calculator.sub(a, b);
        assertThat(actual).isEqualTo(expected);
    }

}

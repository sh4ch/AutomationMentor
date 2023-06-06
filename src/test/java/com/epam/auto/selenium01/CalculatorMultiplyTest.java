package com.epam.auto.selenium01;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class CalculatorMultiplyTest extends CalculatorBaseTest {

    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Multiply Data")
    public void multiplyTest(long a, long b, long expected) {
        System.out.printf("multiply %d * %d data provider test\n", a, b);
        long actual = calculator.mult(a, b);
        assertThat(actual).isEqualTo(expected);
    }

}

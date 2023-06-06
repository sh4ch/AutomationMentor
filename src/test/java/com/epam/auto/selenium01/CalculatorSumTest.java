package com.epam.auto.selenium01;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class CalculatorSumTest extends CalculatorBaseTest {

    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Sum Data")
    public void sumTest(long a, long b, long expected) {
        System.out.printf("sum %d + %d data provider test%n", a, b);
        long actual = calculator.sum(a, b);
        assertThat(actual).isEqualTo(expected);
    }

}

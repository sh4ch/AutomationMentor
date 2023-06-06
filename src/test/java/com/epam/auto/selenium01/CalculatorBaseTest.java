package com.epam.auto.selenium01;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public abstract class CalculatorBaseTest {

    protected Calculator calculator;

    @BeforeSuite
    public void setUpSuite() {
        System.out.println(CalculatorBaseTest.class.getSimpleName() + " before suite method\n");
    }

    @BeforeClass
    public void setUpClass() {
        System.out.println(this.getClass().getSimpleName() + " before class method\n");
        this.calculator = new Calculator();
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("before method\n");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("after method\n");
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println(this.getClass().getSimpleName() + " after class method\n");
        this.calculator = null;
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println(CalculatorBaseTest.class.getSimpleName() + " after suite method\n");
    }
}

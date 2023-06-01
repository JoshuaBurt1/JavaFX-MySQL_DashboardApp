package com.example.joshuaburt_comp1011sec005_labex02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void addTest1()  { //method to be tested must be in this format: public double testAdd(double num1,double num2)
        CalculatorController calculatorController = new CalculatorController(); //Version A: instantiate object
        assertEquals(4, calculatorController.testAdd(2,2)); //if testAdd method was num1*num2, this would pass --> so multiple tests
    }
    @Test
    void addTest2() { //same function as addTest1, but throws an illegal argument exception
        CalculatorController calculatorController = new CalculatorController(); //Version A: instantiate object
        if(calculatorController.testAdd(4,4)!=8){
            throw new IllegalArgumentException("Number must equal 8.");
        }
    }

    @Test
    void minusTest() {
        var calculatorController = new CalculatorController(); //Version B: instantiate object
        assertEquals(0, calculatorController.testSubtract(4,4));
    }

    @Test
    void multiplyTest() {
        var calculatorController = new CalculatorController(); //Version B: instantiate object
        assertEquals(16, calculatorController.testMultiply(4,4));
    }

    @Test
    void divideTest() {
        var calculatorController = new CalculatorController(); //Version B: instantiate object
        assertEquals(1, calculatorController.testDivide(4,4));
    }
}
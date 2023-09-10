package com.example.sos_game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {

    @Test
    void add() {
        SimpleCalculator calculator = new SimpleCalculator();
        assertEquals(4, calculator.addIntegers(2, 2));
        assertTrue(calculator.addIntegers(7, 3) == 10);
    }
}
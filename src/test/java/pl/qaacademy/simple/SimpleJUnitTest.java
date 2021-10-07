package pl.qaacademy.simple;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class SimpleJUnitTest {
    @Test
    public void addingTwoArgumentsProducesItsSum() {
        Calculator calc = new Calculator();
        double expectedResult = 5;
        double actualResult = calc.add(2,3);
        assertEquals(actualResult, expectedResult);
    }
}

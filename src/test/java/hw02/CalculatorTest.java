package hw02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    static Calculator c;

    @BeforeAll
    public static void initCalculator() {
        c = new Calculator();
    }

    @Test
    public void divide_divideByZero_ArithmeticException() {
        Assertions.assertThrows(ArithmeticException.class, () -> c.divide(5, 0));
    }

    @Test
    public void divide_divideTenByTwo_5() {
        Assertions.assertEquals(5, c.divide(10, 2));
    }

    @Test
    public void add_addThreeAndFive_8() {
        Assertions.assertEquals(8, c.add(3, 5));
    }

    @Test
    public void subtract_subtractMinusFiveFromThree_8() {
        Assertions.assertEquals(8, c.subtract(3, -5));
    }

    @Test
    public void multiply_multiplyFiveAndMinusTwo_minus10() {
        Assertions.assertEquals(-10, c.multiply(5, -2));
    }

    @Test
    public void multiply_multiplyMinusTwoAndMinusTwo_4() {
        Assertions.assertEquals(4, c.multiply(-2, -2));
    }

    @Test
    public void multiply_multiplyTwoAndTwo_4() {
        Assertions.assertEquals(4, c.multiply(2, 2));
    }


}

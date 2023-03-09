package hw01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SumTest {

    @Test
    public void sumRecursive_sumOfFour_10() {
        Assertions.assertEquals(10, Sum.sumRecursive(4));
    }

    @Test
    public void sumIterative_sumOfSix_21() {
        Assertions.assertEquals(21, Sum.sumIterative(6));
    }

}

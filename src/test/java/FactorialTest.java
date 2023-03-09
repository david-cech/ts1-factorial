import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactorialTest {

    @Test
    public void factorialRecursive_factorialOfTwo_2() {
        Assertions.assertEquals(2, Factorial.factorialRecursive(2));
    }

    @Test
    public void factorialIterative_factorialOfThree_6() {
        Assertions.assertEquals(6, Factorial.factorialIterative(3));
    }

}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactorialTest {

    @Test
    public void factorialRecursiveTest() {
        Assertions.assertEquals(2, Factorial.factorialRecursive(2));
    }

    @Test
    public void factorialIterativeTest() {
        Assertions.assertEquals(6, Factorial.factorialIterative(3));
    }

}

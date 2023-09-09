package src.test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import src.main.SimpleCalculator;

public class SimpleCalculatorTest {
    @Test
    public void twoPlusTwoEqualsFour() {
        SimpleCalculator calculator = new SimpleCalculator();
        assertEquals(4, calculator.add(2, 2));
    }
}

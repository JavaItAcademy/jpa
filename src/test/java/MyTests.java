import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


//@RunWith(JUnitPlatform.class)
public class MyTests {
    @Test
    public void testEven() {
        TestApp testApp = new TestApp();
        Assertions.assertTrue(testApp.isEven(4), "Error with param 4");
        Assertions.assertFalse(testApp.isEven(5), "Error with param 5");
    }

    @Test
    public void testSquareArea() {
        TestApp testApp = new TestApp();
        Assertions.assertEquals(testApp.areaOfSquare(5), 25);
    }
}

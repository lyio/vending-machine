import lyio.vm.beverage.FritzCola;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeverageTest {
    int expectedPrice = 120;
    String expectedName = "Fritz Cola";
    FritzCola beverage;

    @Before
    public void setup() {
        beverage = new FritzCola();
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        Assert.assertEquals(expectedPrice, beverage.getPrice());
    }

    @Test
    public void getNameReturnsCorrectName() {
        Assert.assertEquals(expectedName, beverage.getName());
    }
}

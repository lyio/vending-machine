import lyio.vm.BeverageResponse;
import lyio.vm.VendingMachine;
import lyio.vm.beverage.BEVERAGES;
import lyio.vm.coin.Coin;
import lyio.vm.coin.CoinFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineTest {
    private VendingMachine machine;
    private List<Coin> coinsFor120Cent = new ArrayList<Coin>() {
        {
            add(CoinFactory.get1EuroCoin());
            add(CoinFactory.get20CentCoint());
        }
    };

    private List<Coin> coinsFor100Cent = new ArrayList<Coin>() {
        {
            add(CoinFactory.get1EuroCoin());
        }
    };

    private List<Coin> coinsFor150Cent = new ArrayList<Coin>() {
        {
            add(CoinFactory.get20CentCoint());
            add(CoinFactory.get20CentCoint());
            add(CoinFactory.get1EuroCoin());
            add(CoinFactory.get10CentCoin());
        }
    };

    @Before
    public void setup() {
        machine = new VendingMachine();
    }

    @Test
    public void emptyRemovesStockCompletely() {
        machine.emptyStock();

        Assert.assertTrue(machine.fritzColaSupply.isEmpty());
        Assert.assertTrue(machine.lipzSchorleSupply.isEmpty());
    }

    @Test
    public void purchaseReturnsBeverageIfInStock() {
        Assert.assertNotNull(machine.purchase(BEVERAGES.FRITZCOLA, coinsFor120Cent));
    }

    @Test
    public void purchaseReturnsCorrectBeverage() {
        final BeverageResponse response = machine.purchase(BEVERAGES.LIPZSCHORLE, coinsFor150Cent);
        Assert.assertEquals(response.beverage.getName(), "Lipz Schorle");
    }

    @Test
    public void purchaseReturnsErrorWhenNotInStock() {
        machine.emptyStock();
        final BeverageResponse response = machine.purchase(BEVERAGES.FRITZCOLA, coinsFor120Cent);
        Assert.assertNotNull(response);
        Assert.assertNull(response.beverage);
    }

    @Test
    public void purchaseReturnsErrorWhenNotEnoughCoins() {
        final BeverageResponse response = machine.purchase(BEVERAGES.FRITZCOLA, coinsFor100Cent);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isSuccess);
        Assert.assertNull(response.beverage);
    }
}

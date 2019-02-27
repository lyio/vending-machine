package lyio.vm;

import lyio.vm.beverage.BEVERAGES;
import lyio.vm.coin.Coin;

import java.util.List;

public interface IVendingMachine {
    /**
     * Stock the vending machine a number of beverages
     * @param beverage Choice of beverage to stock
     * @param count Number of beverages to stock
     */
    void stockMachine(BEVERAGES beverage, int count);

    /**
     * Dispenses the chosen beverage and accepts the coins
     * Returns an error and the original coins if they are insufficient
     * @param beverageChoice
     * @param coins
     * @return
     */
    BeverageResponse purchase(BEVERAGES beverageChoice, List<Coin> coins);

    /**
     * Removes all product from the machine
     */
    void emptyStock();
}

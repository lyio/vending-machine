package lyio.vm;

import lyio.vm.beverage.IBeverage;
import lyio.vm.coin.Coin;

import java.util.List;

public final class BeverageResponse {
    public final boolean isSuccess;
    public final IBeverage beverage;
    public final List<Coin> change;

    public BeverageResponse(boolean isSuccess, IBeverage beverage, List<Coin> change) {
        this.isSuccess = isSuccess;
        this.beverage = beverage;
        this.change = change;
    }
}

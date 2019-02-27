package lyio.vm.coin;

import org.jetbrains.annotations.NotNull;

public class CoinFactory {

    @NotNull
    public static Coin get10CentCoin() {
        return new Coin(10, "10 Cent");
    }

    @NotNull
    public static Coin get20CentCoint() {
        return new Coin(20, "20 Cent");
    }

    @NotNull
    public static Coin get50CentPoint() {
        return new Coin(50, "50 Cent");
    }

    @NotNull
    public static Coin get1EuroCoin() {
        return new Coin(100, "1 Euro");
    }

    @NotNull
    public static Coin get2EuroCoin() {
        return new Coin(200, "2 Euro");
    }
}

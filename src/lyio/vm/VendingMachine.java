package lyio.vm;

import lyio.vm.beverage.BEVERAGES;
import lyio.vm.beverage.FritzCola;
import lyio.vm.beverage.IBeverage;
import lyio.vm.beverage.LipzSchorle;
import lyio.vm.coin.Coin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class VendingMachine implements IVendingMachine {
    public final Stack<IBeverage> fritzColaSupply = new Stack<>();
    public final Stack<IBeverage> lipzSchorleSupply = new Stack<>();

    private final Map<Integer, List<Coin>> coinage = new HashMap<>();

    public VendingMachine() {
        stockMachine(BEVERAGES.FRITZCOLA, 2);
        stockMachine(BEVERAGES.LIPZSCHORLE, 2);
    }

    @Override
    @NotNull
    public BeverageResponse purchase(@NotNull BEVERAGES beverageChoice, @NotNull List<Coin> coins) {
        switch (beverageChoice) {
            case FRITZCOLA:
                return dispenseBeverage(fritzColaSupply, new FritzCola(), coins);
            case LIPZSCHORLE:
                return dispenseBeverage(lipzSchorleSupply, new LipzSchorle(), coins);
        }

        return null;
    }

    @Override
    public void emptyStock() {
        fritzColaSupply.clear();
        lipzSchorleSupply.clear();
    }

    @Override
    public void stockMachine(BEVERAGES beverage, int count) {
        switch (beverage) {
            case FRITZCOLA:
                for (int i = 0; i <= count; i++) {
                    fritzColaSupply.push(new FritzCola());
                }
                break;
            case LIPZSCHORLE:
                for (int i = 0; i <= count; i++) {
                    lipzSchorleSupply.push(new LipzSchorle());
                }
                break;
        }
    }

    private BeverageResponse dispenseBeverage(Stack<IBeverage> supply, IBeverage choice, List<Coin> coins) {
        final int coinValue = coins.stream().mapToInt(Coin::getValue).sum();
        final List<Coin> change = checkChangeAvailability(choice.getPrice() - coinValue);
        if(coinValue > choice.getPrice() && null == change) {
            return new BeverageResponse(false, null, null);
        }

        if(checkSupplyAndCoinage(supply, choice, coinValue)) {
            acceptCoins(coins);
            return new BeverageResponse(true, supply.pop(), new ArrayList<>());
        }

        return new BeverageResponse(false, null, null);
    }

    private List<Coin> checkChangeAvailability(int requiredChange) {
        if(requiredChange == 0) return new ArrayList<>();
        // no change at all
        if(coinage.isEmpty()) return null;

        final Set<Integer> values = coinage.keySet();
        // total value of available change too low
        if(values.stream().mapToInt(i -> i).sum() < requiredChange) return null;

        return null;
    }

    private boolean checkSupplyAndCoinage(Stack<IBeverage> supply, IBeverage beverage, int coinValue) {
        return !supply.isEmpty() && beverage.getPrice() <= coinValue;
    }

    private void acceptCoins(List<Coin> coins) {
        coins.forEach((Coin coin) -> {
            if(coinage.containsKey(coin.getValue())) {
                coinage.get(coin.getValue()).add(coin);
            } else {
                coinage.put(coin.getValue(), new ArrayList<Coin>() {
                    {
                        add(coin);
                    }
                });
            }
        });
    }
}

package lyio.vm.beverage;

public class Beverage implements IBeverage {
    protected final int price;

    protected final String name;

    public Beverage(int price, String name) {
        this.price = price;
        this.name = name;
    }
    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

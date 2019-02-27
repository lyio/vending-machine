package lyio.vm.coin;

public final class Coin {
    private final int value;
    private final String name;

    public Coin(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}

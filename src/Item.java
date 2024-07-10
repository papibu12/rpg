package src;

public class Item {
    private String name;
    private ItemType type;
    private int effectValue;

    public Item(String name, ItemType type, int effectValue) {
        this.name = name;
        this.type = type;
        this.effectValue = effectValue;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getEffectValue() {
        return effectValue;
    }

    public enum ItemType {
        POTION
    }
}


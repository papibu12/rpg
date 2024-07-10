package src;

public class Potion extends Item {

    public Potion(String name, int effectValue) {
        super(name, ItemType.POTION, effectValue);
    }


    public void use(Player player) {
        player.hp += getEffectValue();
        if (player.hp > player.getMaxHp()) {
            player.hp = player.getMaxHp();
        }
        System.out.println("Vous avez utilisé " + getName() + " et restauré " + getEffectValue() + " HP.");
    }
}

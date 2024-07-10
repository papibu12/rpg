package src;

public class Player extends Entity {

    public static final String strenght = null;
    // aditional player stats
    public int gold, restLeft, pots;
    public int vitality, attunement, endurance, strength, dexterity, resistance, intelligence, faith;
    public boolean Offéria = false;
    public int OffériaEncounter = 0;
    private Inventory inventory;

    // constructeur spécifique au joueur
    public Player(String name, int vitality) {
        super(name, calculateMaxHp(vitality), 0, 3, 1, vitality);
        this.vitality = vitality;
        this.attunement = 11;
        this.endurance = 10;
        this.strength = 10;
        this.dexterity = 10;
        this.resistance = 10;
        this.intelligence = 10;
        this.faith = 10;
        this.gold = (int) (Math.random() * 15) + 10;
        this.restLeft = 1;
        this.pots = 3;
        this.maxHp = 3 * this.vitality;
        this.hp = this.maxHp;
        this.inventory = new Inventory();
        chooseClass();
    }

    private static int calculateMaxHp(int vitality) {
        return 4 * vitality;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int maxDMG(Weapon w) {
        int max = 0;
        switch (w.getNameWeapon()) {
            case "Sword":
                max = (int) ((w.getScalingDmg() * strength) + (w.getScalingDmg() * dexterity));
                break;
            case "Talisman":
                max = (int) ((w.getScalingDmg() * faith) + (w.getScalingDmg() * dexterity)) * (attunement / 4);
                break;
            case "Stick":
                max = (int) ((w.getScalingDmg() * intelligence) + (w.getScalingDmg() * dexterity)) * (attunement / 4);
                break;
            case "Axe":
                max = (int) ((w.getScalingDmg() * dexterity));
                break;
        }
        return max;
    }

    @Override
    public int attack(Weapon w) {
        int damage = 0;
        int maximum, minimum;
        switch (w.getNameWeapon()) {
            case "Sword":
                maximum = (int) ((w.getScalingDmg() * strength) + (w.getScalingDmg() * dexterity));
                minimum = w.getBaseWeapon();
                damage = (int) (Math.random() * (maximum - minimum)) + minimum;
                break;
            case "Talisman":
                maximum = (int) ((w.getScalingDmg() * faith) + (w.getScalingDmg() * dexterity));
                minimum = w.getBaseWeapon();
                damage = (int) (Math.random() * (maximum - minimum)) + minimum;
                damage *= (attunement / 4);
                break;
            case "Stick":
                maximum = (int) ((w.getScalingDmg() * intelligence) + (w.getScalingDmg() * dexterity));
                minimum = w.getBaseWeapon();
                damage = (int) (Math.random() * (maximum - minimum)) + minimum;
                damage *= (attunement / 4);
                break;
            case "Axe":
                maximum = (int) ((w.getScalingDmg() * dexterity));
                minimum = w.getBaseWeapon();
                damage = (int) (Math.random() * (maximum - minimum)) + minimum;
                break;
        }
        return damage;
    }

    @Override
    public int defend() {
        int maximum = (endurance / 2) + resistance + level;
        int minimum = (endurance / 4) + (resistance / 2) + level;
        return (int) (Math.random() * (maximum - minimum)) + minimum;
    }

    public void chooseClass() {
        boolean classSet = false;
        do {
            GameLogic.clearConsole();
            GameLogic.printHeading("Choisissez votre classe de départ.");
            System.out.println("(1) - Chevalier\n(2) - Cleric\n(3) - Magicien\n(4) - Bandit\n(5) - Prisonier");
            int input = GameLogic.readInt("->", 5);
            switch (input) {
                case 1:
                    setClassAttributes(10, 0, 8, 9, 2, 11, 0, 5);
                    classSet = true;
                    break;
                case 2:
                    setClassAttributes(8, 4, 5, 0, 7, 7, 0, 14);
                    classSet = true;
                    break;
                case 3:
                    setClassAttributes(5, 4, 5, 0, 8, 8, 15, 0);
                    classSet = true;
                    break;
                case 4:
                    setClassAttributes(10, 0, 7, 3, 14, 8, 0, 8);
                    classSet = true;
                    break;
                case 5:
                    setClassAttributes(6, 3, 6, 6, 6, 6, 6, 6);
                    classSet = true;
                    break;
            }
            this.level = 1;
            this.maxHp = (4 * this.vitality) + (level * 2);
            this.hp = this.maxHp;
        } while (!classSet);
    }

    private void setClassAttributes(int vitality, int attunement, int endurance, int strength, int dexterity, int resistance, int intelligence, int faith) {
        this.vitality = vitality;
        this.attunement = attunement;
        this.endurance = endurance;
        this.strength = strength;
        this.dexterity = dexterity;
        this.resistance = resistance;
        this.intelligence = intelligence;
        this.faith = faith;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItemToInventory(Item item) {
        inventory.addItem(item);
    }

    public void removeItemFromInventory(Item item) {
        inventory.removeItem(item);
    }

    public void showInventory() {
        inventory.showInventory();
    }

    public void useItem(Item item) {
        if (inventory.hasItem(item)) {
            switch (item.getType()) {
                case POTION:
                    hp += item.getEffectValue();
                    if (hp > maxHp) hp = maxHp;
                    System.out.println("You used " + item.getName() + " and restored " + item.getEffectValue() + " HP.");
                    break;
                // Add other item types if needed
            }
            inventory.removeItem(item);
        } else {
            System.out.println("You don't have this item.");
        }
    }
    
    public int getMaxHp(){
        return maxHp;
    }

}
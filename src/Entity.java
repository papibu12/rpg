package src;

public abstract class Entity {

    // variables / attribut que tous les personnages ont
    public String name;
    public int maxHp, hp, xp, xpRequire, level;

    // Constructeur pour les personnages
    public Entity(String name, int maxHp, int xp, int xpRequire, int vitality, int level) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.xp = xp;
        this.xpRequire = xpRequire;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    // methodes que tous les personnages ont
    public abstract int attack(Weapon w);

    public abstract int defend();

}

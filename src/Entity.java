package src;

public abstract class Entity {

    // variables / attribut que tous les personnages ont
    public String name;
    // public String classR;
    public int maxHp, hp, xp, xpRequire, Numlevel;

    // Constructeur pour les personnages
    public Entity(String name, int maxHp, int xp, int xpRequire, int vitality, int Numlevel) {
        this.name = name;
        this.maxHp = maxHp;
        this.xp = xp;
        // this.xpTrack = xpTrack;
        this.xpRequire = xpRequire;
        this.hp = maxHp;
    }

    public String getName() {
        return name;
    }

    // methodes que tous les personnages ont
    public abstract int attack(Weapon w);

    public abstract int defend();

}

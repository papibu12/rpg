package src;

public class Player extends Entity {

    // aditional player stats
    int gold, restleft, pots;
    public int vitality, attunement, endurance, strenght, dexterity, resistance, intelligence, faith;
    public boolean Offéria = false;

    // constructeur spécifique au joueur
    public Player(String name, int maxHp, int xp, int xpRequire, int vitality, int Numlevel) {

        // calling constructor of superclass
        super(name, maxHp, 0, 7, vitality, 1);

        // set additional stats;
        this.gold = 5;
        this.restleft = 1;
        this.pots = 1;

        this.vitality = 5;
        this.attunement = 11;
        this.endurance = 10;
        this.strenght = 10;
        this.dexterity = 10;
        this.resistance = 10;
        this.intelligence = 10;
        this.faith = 10;

        // ajout les bonnes valeurs en fonction de vitality;
        this.maxHp = 3 * this.vitality;
        this.hp = this.maxHp;

        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
        // let the player choose a Classes when creating a new character
        chooseClasse();
    }

    public int getStrenght() {
        return strenght;
    }

    public int getDexterity(){
        return dexterity;
    }


    // methode spécifique au joueurs
    @Override
    public int attack(Weapon w) {
        int damage = 5;
        if(w.getNameWeapon() == "Sword"){
            damage = (int) (Math.random() * (w.getScalingDmg() * strenght) + (w.getScalingDmg() * dexterity))
            + w.getBaseWeapon();
        }
        if(w.getNameWeapon() == "Mace"){
            damage = (int) (Math.random() * (w.getScalingDmg() * faith) + (w.getScalingDmg() * dexterity))
            + w.getBaseWeapon();
        }
        if(w.getNameWeapon() == "Stick"){
            damage = (int) (Math.random() * (w.getScalingDmg() * intelligence) + (w.getScalingDmg() * dexterity))
            + w.getBaseWeapon();
        }
        if(w.getNameWeapon() == "Axe"){
            damage = (int) (Math.random() * (w.getScalingDmg() * dexterity))
            + w.getBaseWeapon();
        }

        return damage;
    }

    @Override
    public int defend() {
        return (int) (Math.random() * ((endurance / 4) + (resistance / 2) + (Numlevel))) + 1;
    }

    public void chooseClasse() {
        boolean ClasseSet = false;
        do {
            GameLogic.clearConsole();
            GameLogic.printHeading("Choisisez votre classe de départ.");
            System.out.println("(1) - Chevalier \n(2) - Cleric \n(3) - Magicien \n(4) - Bandit");
            int input = GameLogic.readInt("->", 4);
            //45 points de base par classe
            //chevalier
            if (input == 1) {
                this.vitality = 12;
                this.attunement = 0;
                this.endurance = 5;
                this.strenght = 10;
                this.dexterity = 5;
                this.resistance = 10;
                this.intelligence = 2;
                this.faith = 1;
                ClasseSet = true;
            }
            //cleric
            if (input == 2) {
                this.vitality = 8;
                this.attunement = 3;
                this.endurance = 5;
                this.strenght = 0;
                this.dexterity = 8;
                this.resistance = 7;
                this.intelligence = 2;
                this.faith = 12;
                ClasseSet = true;
            }
            //Magicien
            if (input == 3) {
                this.vitality = 6;
                this.attunement = 5;
                this.endurance = 5;
                this.strenght = 0;
                this.dexterity = 8;
                this.resistance = 6;
                this.intelligence = 15;
                this.faith = 0;
                ClasseSet = true;

            }if (input == 4) {
                this.vitality = 10;
                this.attunement = 0;
                this.endurance = 5;
                this.strenght = 6;
                this.dexterity = 14;
                this.resistance = 8;
                this.intelligence = 0;
                this.faith = 7;
                ClasseSet = true;
            }
            this.Numlevel = 1;
            this.maxHp = 3 * this.vitality;
            this.hp = this.maxHp;
        } while (!ClasseSet);
    }
}
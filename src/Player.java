package src;

public class Player extends Entity {

    // aditional player stats
    int gold, restleft, pots;
    public int vitality, attunement, endurance, strenght, dexterity, resistance, intelligence, faith;
    public boolean Offéria = false;
    public int OffériaEncounter = 0;

    // constructeur spécifique au joueur
    public Player(String name, int maxHp, int xp, int xpRequire, int vitality, int Numlevel) {

        // calling constructor of superclass
        super(name, maxHp, 0, 3, vitality, 1);

        // set additional stats;
        this.gold = (int) Math.random() * (25 - 10) + 10;
        this.restleft = 1;
        this.pots = 3;

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

    public int maxDMG(Weapon w){
        int max = 0;

        if(w.getNameWeapon() == "Sword"){
            max = (int) ((w.getScalingDmg() * strenght) + (w.getScalingDmg() * dexterity));
        }
        if(w.getNameWeapon() == "Talisman"){
            max = (int) ( (w.getScalingDmg() * faith) + (w.getScalingDmg() * dexterity));
            max = max * (attunement / 4);
        }
        if(w.getNameWeapon() == "Stick"){
            max = (int) ( (w.getScalingDmg() * intelligence) + (w.getScalingDmg() * dexterity));
            max = max * (attunement / 4);
        }
        if(w.getNameWeapon() == "Axe"){
            max = (int) ((w.getScalingDmg() * dexterity));
        }
 
        return max;
    }

    // methode spécifique au joueurs
    @Override
    public int attack(Weapon w) {
        int damage = 0;
        if(w.getNameWeapon() == "Sword"){
            int maximum =  (int) ((w.getScalingDmg() * strenght) + (w.getScalingDmg() * dexterity));
            int minimum = w.getBaseWeapon();
            damage = (int) (Math.random() * maximum - minimum) + minimum;
        }
        if(w.getNameWeapon() == "Talisman"){
            int maximum = (int) ((w.getScalingDmg() * faith) + (w.getScalingDmg() * dexterity));
            int minimum = w.getBaseWeapon(); 
            damage = (int) (Math.random() * maximum- minimum) + minimum;
            damage = damage * (attunement / 4);
        }
        if(w.getNameWeapon() == "Stick"){
            int maximum = (int) ((w.getScalingDmg() * intelligence) + (w.getScalingDmg() * dexterity));
            int minimum = w.getBaseWeapon();
            damage = (int) (Math.random() * maximum - minimum) + minimum;
            damage = damage * (attunement / 4);
        }
        if(w.getNameWeapon() == "Axe"){
            int maximum = (int) (w.getScalingDmg() * dexterity);
            int minimum = w.getBaseWeapon();
            damage = (int) (Math.random() * maximum - minimum ) + minimum;
        }

        return damage;
    }

    @Override
    public int defend() {
        int maximum = (endurance / 2) + resistance + Numlevel;
        int minimum = (endurance / 4) + (resistance / 2) + Numlevel;
        return (int) (Math.random() * (maximum - minimum)) + minimum;
    }

    public void chooseClasse() {
        boolean ClasseSet = false;
        do {
            GameLogic.clearConsole();
            GameLogic.printHeading("Choisisez votre classe de départ.");
            System.out.println("(1) - Chevalier \n(2) - Cleric \n(3) - Magicien \n(4) - Bandit\n(5) - Prisonier");
            int input = GameLogic.readInt("->", 5);
            //45 points de base par classe
            //chevalier
            if (input == 1) {
                this.vitality = 10;
                this.attunement = 0;
                this.endurance = 8;
                this.strenght = 9;
                this.dexterity = 2;
                this.resistance = 11;
                this.intelligence = 0;
                this.faith = 5;
                ClasseSet = true;
            }
            //cleric
            if (input == 2) {
                this.vitality = 8;
                this.attunement = 4;
                this.endurance = 5;
                this.strenght = 0;
                this.dexterity = 7;
                this.resistance = 7;
                this.intelligence = 0;
                this.faith = 14;
                ClasseSet = true;
            }
            //Magicien
            if (input == 3) {
                this.vitality = 5;
                this.attunement = 4;
                this.endurance = 5;
                this.strenght = 0;
                this.dexterity = 8;
                this.resistance = 8;
                this.intelligence = 15;
                this.faith = 0;
                ClasseSet = true;

            }
            //bandit
            if (input == 4) {
                this.vitality = 10;
                this.attunement = 0;
                this.endurance = 7;
                this.strenght = 3;
                this.dexterity = 14;
                this.resistance = 8;
                this.intelligence = 0;
                this.faith = 8;
                ClasseSet = true;
            }
            
            //Prisonier
            if (input == 5) {
                this.vitality = 6;
                this.attunement = 3;
                this.endurance = 6;
                this.strenght = 6;
                this.dexterity = 6;
                this.resistance = 6;
                this.intelligence = 6;
                this.faith = 6;
                ClasseSet = true;
            }
            this.Numlevel = 1;
            this.maxHp = (4 * this.vitality) + (Numlevel * 2);
            this.hp = this.maxHp;
        } while (!ClasseSet);
    }
}
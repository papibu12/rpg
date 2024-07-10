package src;

public class Weapons {
    int minimum;
    int maximum;

    Weapons(){

    }
    Weapons(int minimum, int maximum){
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int damage(Player p){
        minimum = 5;
        maximum = p.strength + p.dexterity + p.intelligence + p.faith + 10;
        return (int)(Math.random()*maximum) + minimum;
    }
}

package src;

public class Enemy extends Entity {

    int playerXp;
    int Numlevel;

    public Enemy(String name, int playerXp, int Numlevel) {

        super(name, (int)(Math.random() * (2 + playerXp + Numlevel)) + 1, (int)(Math.random() * (2 + Numlevel)) + 1, 0,0,5 );
        this.playerXp = playerXp;
         //(int) (Math.random() * ((Numlevel * playerXp) / 2) + 1)
        //(int) (Math.random() * ((playerXp * Math.pow(Numlevel, 2)) / 3) + 5)
        // ((Math.pow(playerXp, 2)* Math.pow(NumLevel, 2))/2) + 5
        // outdated formula maxhp : (int) (Math.random() * playerXp + playerXp / 3 + 5)
        // (Math.pow(Numlevel,2)/(playerXp+1))
        // outdated formula xpgiven on death : (int) (Math.random() * playerXp / 4 + 2)
    }

    @Override
    public int attack(Weapon w) {
        //return (int) (Math.random() * (playerXp / 4 + 1) + xp / 4 + 3);
        return (int) (Math.random() * (playerXp * Numlevel + 1 )) + Numlevel;


    }

    @Override
    public int defend() {
        return (int) (Math.random() * (playerXp / 4 + 1) + xp / 4 + 3);
    }
}

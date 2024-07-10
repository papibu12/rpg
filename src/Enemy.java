package src;

public class Enemy extends Entity {

    private int playerXp;

    public Enemy(String name, int playerXp, int level) {
        super(name, calculateMaxHp(playerXp, level), generateRandomXp(level), 0, level, level);
        this.playerXp = playerXp;
    }

    private static int calculateMaxHp(int playerXp, int level){
        return (int) (Math.random() * (10 * level) + (playerXp / 4)) + (5 * level) + (playerXp / 4);
    }

    private static int generateRandomXp(int level){
        return (int) (Math.random() * (2 + level)) + 1;
    }

    @Override
    public int attack(Weapon w) {
        int maximum = (xp * 2) + (playerXp / 2) + 1;
        int minimum = playerXp + 1;
       
        return (int) (Math.random() * (maximum - minimum)) + minimum;

         //return (int) (Math.random() * (playerXp / 4 + 1) + xp / 4 + 3);
    }

    @Override
    public int defend() {
        int maximum = playerXp + xp;
        int minimum = (playerXp / 2) + xp;
        return (int) (Math.random() * (maximum - minimum)) + minimum;
    }
}

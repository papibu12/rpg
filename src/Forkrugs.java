package src;

public class Forkrugs {
    
    public static void encounter(Weapon w, Player p){

        GameLogic.clearConsole();
        String name = "forkrugs";
        GameLogic.printHeading(Color.YELLOW + name + Color.RESET + " créature resemblant à une forge sur patte");
        System.out.println("...");
        GameLogic.printSeperator(50);
        System.out.println("(1) la tuer\n(2) commercer\n(3) partir");
        int input1 = GameLogic.readInt("-> ", 3);
        if (input1 == 1) {
            GameLogic.clearConsole();
            GameLogic.printHeading("Le Forkrugs se transforme pour se battre.");
            GameLogic.specialBattle(name);
        } else if (input1 == 2) {
            GameLogic.clearConsole();
            //initialisation prix
            int maximum = (w.getBaseWeapon() + 10) /2;
            int minimum = (w.getBaseWeapon() + 7) /2;
            int price = (int) (Math.random() * maximum - minimum) + minimum;
            
            GameLogic.printHeading(Color.YELLOW + "ForKrugs " + Color.RESET + "Grug grugh ?");
            System.out.println("- Niveau d'arme +1 : " + price + " gold.");
            GameLogic.printSeperator(50);
            System.out.println("Vous avez " + Color.YELLOW + p.gold +  Color.RESET + " gold");
            GameLogic.printSeperator(50);
            System.out.println("(1) oui\n(2) non");
            int input2 = GameLogic.readInt("-> ", 2);
            if (input2 == 1) {
                GameLogic.clearConsole();
                if (p.gold >= price) {
                    GameLogic.printHeading("Votre arme a été améliorée");
                    w.setBaseDmg(4 + w.getBaseWeapon());
                } else {
                    GameLogic.printHeading("Vous n'avez pas assez.");
                    GameLogic.anythingToContinue();
                }
            }
        } else {
            GameLogic.anythingToContinue();
        }

    }
}

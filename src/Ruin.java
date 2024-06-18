package src;

public class Ruin {
    
    public static void encounter(Weapon w, Player p){
        String[] ruins = {"'Oolacile", "e Xalv", "es Kanashy's"};
        String nameRuin = ruins[(int) (Math.random() * ruins.length)];
        GameLogic.clearConsole();
        GameLogic.printHeading( Color.BLUE + "Ruin d" + nameRuin + Color.RESET + " | Vous avez découvert des ruines");
        System.out.println("Ses ruines recouvertes autant de végétations que\nde craquelures semble t'avoir été habité autre\nvois par une ancienne civilisation");
        GameLogic.printSeperator(50);
        System.out.println("Que faites-vous ?\n(1) fouilliez les ruines\n(2) se reposer\n(3) partir");
        int input = GameLogic.readInt("-> ", 3);
        GameLogic.clearConsole();
        if(input == 1){
            int maximum = 10;
            int minimum = 7;
            int luck = (int)(Math.random() * maximum - minimum) + minimum;
            if(luck <= 3){
                chest(w, p);
            } 
            else if ( luck > 3 && luck <= 6){
                GameLogic.printHeading( Color.BLUE + "Ruin d" + nameRuin + Color.RESET + " | Vous avez trouvé une statue");
                System.out.println("On dirait qu'elle réprésente une ancienne\nDéesse désormait oubliée de tous.");
                GameLogic.printSeperator(50);
                System.out.println("Que faites-vous ?\n(1) Priez la statue\n(2) se reposer\n(3) partir");
                input = GameLogic.readInt("-> ", 3);
                GameLogic.clearConsole();
                if(input == 1){
                    if( 10 <= p.faith){
                        GameLogic.printHeading( Color.BLUE + "Ruin d" + nameRuin + Color.RESET + " | Vous avez découvert un donjon");
                        //Donjon.encounter();
                    } else {
                        GameLogic.printHeading( Color.BLUE + "Ruin d" + nameRuin + Color.RESET + " | Il ne se passe rien");
                        GameLogic.anythingToContinue();
                    }
                }
                else if ( input == 2 ){
                    GameLogic.takeRest();
                } else{
                    GameLogic.printHeading( Color.BLUE + "Ruin d" + nameRuin + Color.RESET + " | Vous avez décidez de dormir à la belle étoile");
                    GameLogic.anythingToContinue();
                }

            } else {
                GameLogic.randomBattle();
            }
        }
        else if(input == 2){
            GameLogic.printHeading( Color.BLUE + "Ruin d" + nameRuin + Color.RESET + " | Vous avez décidez de dormir à la belle étoile");
            GameLogic.takeRest();
        }
        else{
            GameLogic.printHeading( Color.BLUE + "Ruin d" + nameRuin + Color.RESET + " | Vous êtes partie");
        }
        
    }

    public static void chest(Weapon w, Player p){
        int maximum = 10;
        int minimum = 1;
        int luck = (int)(Math.random() * maximum - minimum) + minimum;
        if(luck <= 4){
            maximum = 40;
            minimum = 20;
            int max = 3;
            int min = 1;
            int gold = (int) ((Math.random() * maximum - minimum) + minimum);

            int potion = (int) (Math.random() * max - min) + min;
            GameLogic.printHeading(Color.YELLOW +"Coffre" + Color.RESET + " |");
            System.out.println("Vous avez trouvé un coffre contenant\n" + gold + " golds et " + potion + " potions");
            p.pots = p.pots + potion;
            p.gold = p.gold + gold;
            GameLogic.anythingToContinue();
        }
        else if ( 4 < luck || luck <= 8){
            GameLogic.printHeading(Color.YELLOW +"Coffre" + Color.RESET + " |");
            System.out.println("Vous avez trouvé un coffre contenant\n" + w.getNameWeapon());
            w.setBaseDmg(8 + w.getBaseWeapon());
            GameLogic.anythingToContinue();
        }
        else{
            GameLogic.printHeading(Color.YELLOW +"Coffre" + Color.RESET + " |");
            System.out.println("Vous n'avez rien trouvé ");
            GameLogic.anythingToContinue();
        }
    }
}

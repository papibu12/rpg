package src;
import java.util.Scanner;

public class GameLogic {
    static Scanner scanner = new Scanner(System.in);

    static Player player;
    static Weapon w;

    public static boolean isRunning;

    // random encounters
    public static String[] encounters = { "Forkrugs", "Recluse", "Recluse", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Shop", "Shop","Rest", "Rest","Ruin" };

// 

    // enemy names
    public static String[] enemies = { "Squelette", "rat géant", "Sorcière du claire Lune", "Succube",
            "Golem de Crystalle", "Démon", "méduse", "pyromancien", "papillon solaire", "chevalier corrompue",
            "Chevalier Noir", "géant"
    };

    //shop name
    public static String[] shops = {"Inna", "Agathe", "Gilles", "Claudine"};

    // Story elements
    public static int place = 0, act = 1;
    public static String[] places = { "Everlasting Mountaing", "Haunted Landline", "Lost Swamp", "Desert of Shakrim", "Lost Kingdom of Romyien" };

    // methode pour obtenir les input de l'utilisateur
    public static int readInt(String prompt, int userChoices) {
        int input;

        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                input = -1;
                System.out.println("Veuilliez Saisir un Entier !");
            }
        } while (input < 1 || input > userChoices);
        return input;
    }

    // methode pour émuler le fait de nettoyer la console
    public static void clearConsole() {
        for (int i = 0; i < 100; i++)
            System.out.println();
    }

    // methode pour afficher un separateur avec n longeur
    public static void printSeperator(int n) {
        for (int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();
    }

    // afficher une en tête
    public static void printHeading(String title) {
        printSeperator(50);
        System.out.println(title);
        printSeperator(50);
    }

    // methode pour arrêter le jeu tant que le joueur ne fait rien
    public static void anythingToContinue() {
        System.out.println("\nEntrez n'importe quoi pour continer...");
        scanner.next();
    }

    // method to start game
    public static void startGame() {
        boolean nameSet = false;
        String name;
        // print title screen
        clearConsole();
        printHeading("L'héritage de Lilith");
        printHeading("Text RPG par Papibu");
        anythingToContinue();

        // getting the player name
        do {
            clearConsole();
            printHeading("Quel est Votre Nom ?");
            name = scanner.next();
            // asking the player if he want to correct his choice
            clearConsole();
            printHeading("Votre nom est bien " + Color.GREEN_BRIGHT + name + Color.RESET + " ?");
            System.out.println("(1) Oui");
            System.out.println("(2) non");
            int input = readInt("-> ", 2);
            if (input == 1)
                nameSet = true;
        } while (!nameSet);

        // print story intro
        Story.printIntro();

        // create a new object with the name
        player = new Player(name, 0, 0, 0, 0, 1);
        if(player.faith >= 10){
            w = new Talisman(name, 5, 1);
        }
        else if(player.dexterity >= 12){
            w = 
            new Axe(name, 5, 1);
        }
        else if(player.intelligence >= 12){
            w = new Stick(name, 5, 2);
        } else{
            w = new Sword(name, 5, 2);
        }
        // setting isRunning to true, so the game loop
        isRunning = true;
        // start main game loop (next part)
        gameLoop();
    }

    // method that changes the game's value based on player xp
    public static void checkAct() {
        // change acts based on lvl
        if (player.Numlevel >= 5 && act == 1) {
            // increment act and place
            act = 2;
            place = 1;
            // story
            Story.printFirstActOutro();
            // story
            Story.printSecondActIntro();
            enemies[0] = "Evil Mage";
            enemies[1] = "Dark Knigh";
            // etc...
        } else if (player.Numlevel >= 10 && act == 2) {
            // increment act and place
            act = 3;
            place = 2;
            // story
            Story.printSecondActOutro();
            // story
            Story.printThridActIntro();
            enemies[0] = "Swamp Witch";
            enemies[1] = "Stone Golem";
        } else if (player.Numlevel >= 15 && act == 3) {
            // increment act and place
            act = 4;
            place = 3;
            // story
            Story.printThridActOutro();
            // story
            Story.printFourthActIntro();

        }
    }

    // method to calculate a random encounter
    public static void randomEncounter() {
        int encounter = (int) (Math.random() * encounters.length);
        // calling the respective method
        if (encounters[encounter].equals("Battle")) {
            randomBattle();
        } else if (encounters[encounter].equals("Rest")) {
            takeRest();
        } else if (encounters[encounter].equals("Recluse")) {
            recluse();
        } else if (encounters[encounter].equals("Forkrugs")) {
            forkrugs();
        }  else if (encounters[encounter].equals("Ruin")) {
            Ruin();
        } else {
            shop();
        }
    }

    // methode to continue the journey (more next part)
    public static void continueJourney() {
        checkAct(); // check if act must be increased
        if (act !=100){// check if game isn't in last act
            randomEncounter();
        }
    }

    // printing out the most important information about player character
    public static void characterInfo() {
        clearConsole();
        int maximum = (player.endurance / 2) + player.resistance + player.Numlevel;
        int minimum = (player.endurance / 4) + (player.resistance / 2)+ player.Numlevel;
        printHeading(Color.BLUE_BOLD_BRIGHT + "Personnage : " + Color.RESET + player.name + "\t\t\tLevel : " + player.Numlevel);
        System.out.println("Expérience     : " + player.xp + "\t\t\tHp : " + Color.RED_BRIGHT + player.hp + Color.RESET + "/" + Color.RED_BRIGHT + player.maxHp + Color.RESET);
        System.out.println("Expérience Req : " + player.xpRequire);
        printSeperator(50);
        System.out.println("Vitalité    : " + player.vitality);
        System.out.println("Volonté     : " + player.attunement);
        System.out.println("Endurance   : " + player.endurance);
        System.out.println("Force       : " + player.strenght);
        System.out.println("Dextérité   : " + player.dexterity);
        System.out.println("Résistance  : " + player.resistance);
        System.out.println("Intéligence : " + player.intelligence);
        System.out.println("Foi         : " + player.faith);
        printSeperator(50);
        System.out.println("Potion de Soin : " + player.pots + "\t\tGold : " + Color.YELLOW_BRIGHT + player.gold + Color.RESET);
        System.out.println("Feu de Camps : " + player.restleft + "\t\tArmure : " + maximum + "-" + minimum);
        System.out.println("Arme : " + (player.maxDMG(w)) + " - "+ w.getBaseWeapon());
        printSeperator(50);
        anythingToContinue();
    }

    // lvl up stats
    public static void levelUp() {
        clearConsole();
        if (player.xp >= player.xpRequire) {
            printHeading(Color.BLUE_BOLD_BRIGHT + "LevelUp  " + Color.RESET + "| Choissisez une statistique à augmenter");
            System.out.println("(1) Vitalité    : " + player.vitality);
            System.out.println("(2) Volonté     : " + player.attunement);
            System.out.println("(3) Endurance   : " + player.endurance);
            System.out.println("(4) Force       : " + player.strenght);
            System.out.println("(5) Dextérité   : " + player.dexterity);
            System.out.println("(6) Résistance  : " + player.resistance);
            System.out.println("(7) Intéligence : " + player.intelligence);
            System.out.println("(8) Foi         : " + player.faith);
            int input = readInt("->", 8);
            switch (input) {
                case 1:
                    player.vitality = player.vitality + 1;
                    break;
                case 2:
                    player.attunement += 1;
                    break;
                case 3:
                    player.endurance += 1;
                    break;
                case 4:
                    player.strenght += 1;
                    break;
                case 5:
                    player.dexterity += 1;
                    break;
                case 6:
                    player.resistance += 1;
                    break;
                case 7:
                    player.intelligence += 1;
                    break;
                case 8:
                    player.faith += 1;
                    break;
                default:
                    break;
            }
            player.xp = player.xp - player.xpRequire;
            player.xpRequire = (player.xpRequire + (int) (Math.random() * 3 - 1) + 1);
            player.Numlevel += 1;
            player.maxHp = (4 * player.vitality) + (player.Numlevel * 2);
            player.hp += 4;
            if (player.hp >= player.maxHp){
                player.hp = player.maxHp;
            }
        } else {
            printHeading(Color.BLUE_BOLD_BRIGHT + "LevelUp  " + Color.RESET + "| Vous ne pouvez pas Level Up");
            anythingToContinue();
        }
    }

    // shopping /encountering
    public static void shop() {
        clearConsole();
        String nameShop = shops[(int) (Math.random() * shops.length)];
        printHeading( Color.GREEN + "Marchand itinérant : " + Color.RESET + nameShop);
        
        //initialisation des différents prix
        int pricePotion = (int) (Math.random() * (10 + (player.pots * 2))) + 5 + player.pots;
        int priceRepos = (int) (Math.random() * (10 + (player.restleft * 3))) + 10 + player.restleft;

        System.out.println("Venez me voir pour les meilleurs affaires");
        System.out.println("- bénédiction divine : " + Color.YELLOW + pricePotion + Color.RESET +" gold.");
        System.out.println("- Feu de Camps : " + Color.YELLOW + priceRepos + Color.RESET + " gold.");
        printHeading("Vous avez " + Color.YELLOW + player.gold + Color.RESET +" gold.");
        System.out.println("Que voulez-vous acheter ?\n(1) bénédiction divine\n(2) Feu de Camps\n(3) Voler\n(4) Rien");
        int input = readInt("-> ", 4);
        if (input == 1) {
            clearConsole();
            if (player.gold >= pricePotion) {
                printHeading("Vous avez acheter une bénédiction divine \n-" + pricePotion + " gold");
                player.pots++;
                player.gold -= pricePotion;
            } else  {
                printHeading(Color.GREEN + "Marchand itinérant : " + Color.RESET + nameShop);
                System.out.println("Vous n'avez pas assez.");
                anythingToContinue();
            }
        } 
        if (input == 2){
            clearConsole();
            if (player.gold >= priceRepos) {
                printHeading("Vous avez acheter un Feu de Camps \n-" + priceRepos + " gold");
                player.restleft++;
                player.gold -= priceRepos;
            } else  {
                printHeading(Color.GREEN + "Marchand itinérant : " + Color.RESET + nameShop);
                System.out.println("Vous n'avez pas assez.");
                anythingToContinue();
            }
        } 
        if (input == 3){
            clearConsole();
            if(Math.random() * 20 + 1 <= player.dexterity / 2){

                int maximum = (pricePotion + priceRepos) /2;
                int minimum = pricePotion;
                int potionSteal = (int) (Math.random() * (3-0)) + 0;
                int goldSteal =  (int) (Math.random() * (maximum - minimum) ) + minimum;

                printHeading(Color.GREEN + "Marchand itinérant : " + Color.RESET + nameShop);
                System.out.println("Vous avez volez " + potionSteal + " potions et " + goldSteal + " gold.");
                player.pots = player.pots + potionSteal;
                player.gold = player.gold +goldSteal;
                anythingToContinue();
            }else{
                clearConsole();
                printHeading(Color.GREEN + "Marchand itinérant : " + Color.RESET + nameShop);
                System.out.println("oh Voleur !");
                anythingToContinue();
                specialBattle(nameShop);
            }
        }
        if (input == 4){
            clearConsole();
            printHeading("Ah, revenez me voir plus tard...\nAvec des pièces la prochaine fois...\nah ah ah...");
            anythingToContinue();
        }
        
    }

    public static void specialBattle(String name) {
        clearConsole();
        battle(new Enemy(name, player.xp, player.Numlevel));
    }

    //NPC
    public static void recluse() {
        if(player.Offéria == false){
            Offeria.firstEncounter(player);
        } else{
            Offeria.encounter(player);
        }
    }

    public static void forkrugs() {
        Forkrugs.encounter(w, player);
    }

    public static void Ruin(){
        Ruin.encounter(w, player);
    }


    // takin a rest
    public static void takeRest() {
        clearConsole();
        if (player.restleft >= 1) {
            printHeading("Voulez-vous vous reposer auprès d'un de feu ?\n(" + player.restleft + " repot(s) restant(s)).\t\tPv : " + player.hp +"/"+ player.maxHp);
            System.out.println("(1) oui\n(2) non");
            int input = readInt("-> ", 2);
            clearConsole();
            if (input == 1) {
                printHeading(Color.RED + "Feu" + Color.RESET +" | Vous vous êtes reposé(e).");
                if (player.hp < player.maxHp) {
                    int maximum = player.vitality + player.Numlevel + player.endurance;
                    int minimum = player.Numlevel + player.endurance + 5; 
                    int hpRestored = (int) (Math.random() * (maximum - minimum)) + minimum;
                    player.hp += hpRestored;
                    if (player.hp >= player.maxHp)
                        player.hp = player.maxHp;
                    System.out.println("Et vous vous êtes restauré(e) " + Color.RED + hpRestored + Color.RESET +" Hp.");
                    System.out.println("Hp : " +player.hp + "/" + player.maxHp + " Hp.");
                    player.restleft--;
                }
                anythingToContinue();
            } else {
                printHeading(Color.RED + "Feu" + Color.RESET +" | Vous ne vous sentez pas fatigué(e).");
                anythingToContinue();
            }
        }
    }

    // creating a random battle
    public static void randomBattle() {
        clearConsole();
        printHeading("Le Mal vous a Trouvé, Vous allez vous Battre !");
        anythingToContinue();
        // creatting a new enemy with random name
        battle(new Enemy(enemies[(int) (Math.random() * enemies.length)], player.xp, player.Numlevel));
    }

    // the main Battle method
    public static void battle(Enemy enemy) {
        // main loop
        while (true) {
            clearConsole();
            printHeading(Color.MAGENTA + enemy.name + Color.RESET + "\nHp : " + Color.RED + enemy.hp + Color.RESET + "/" + Color.RED + enemy.maxHp + Color.RESET);
            System.out.println(Color.GREEN_BRIGHT + player.name + Color.RESET + "\nHp : " + Color.RED + player.hp + Color.RESET + "/" + Color.RED + player.maxHp + Color.RESET);
            printSeperator(50);
            System.out.println("Choisisez une action");
            printSeperator(50); //20
            System.out.println("(1) Combattre\n(2) Utiliser un objet\n(3) Fuir");
            int input = readInt("-> ", 3);
            // react accordingly to player input
            if (input == 1) {
                // fight
                // calculate dmg and dmg took
                int dmg = player.attack(w) - enemy.defend();
                int dmgTook = enemy.attack(w) - player.defend();
                // check that dmg and dmgtook isn't negatif
                if (dmgTook < 0) {
                    // add some dmg if player defend very well
                    dmg -= dmgTook / 2;
                    dmgTook = 0;
                }
                if (dmg < 0)
                    dmg = 0;
                player.hp -= dmgTook;
                enemy.hp -= dmg;
                // print new info battle
                clearConsole();
                printHeading("Combat |");
                System.out.println("Vous avez infligé " + dmg + " dmg à " + Color.MAGENTA + enemy.name + Color.RESET + ".");
                printSeperator(50);
                System.out.println((Color.MAGENTA + enemy.name + Color.RESET+ " vous à infligé " + dmgTook + " dmg."));
                printSeperator(50);
                anythingToContinue();
                // check if player is alive
                if (player.hp <= 0) {
                    playerDied(); // methode at this end of the game
                    break;
                } else if (enemy.hp <= 0) {
                    clearConsole();
                    printHeading("Combat | " + Color.YELLOW_BOLD_BRIGHT + "Vous avez Triomphé" + Color.RESET);
                    player.xp += enemy.xp;
                    System.out.println("+ " + enemy.xp + "xp !");
                    // random
                    boolean addRest = (Math.random() * 5 + 1 <= 2.25);
                    int goldEarned = (int) (Math.random() * enemy.xp);
                    if (addRest) {
                        player.restleft++;
                        System.out.println("Vous avez obtenu un feu de camp supplémentaire !");
                    }
                    if (goldEarned > 0) {
                        player.gold += goldEarned;
                        System.out.println("Vous avez obtenu "+ Color.YELLOW_BOLD_BRIGHT + goldEarned + Color.RESET + " Gold");
                    }
                    anythingToContinue();
                    break;
                }
            } else if (input == 2) {
                // inventory
                clearConsole();
                if (player.pots > 0 && player.hp < player.maxHp) {
                    int maximum = player.vitality + (player.endurance * 2);
                    int minimum = player.vitality + player.endurance;
                    int soin = (int) (Math.random() * (maximum - minimum)) + minimum;
                    player.hp += soin;
                    if (player.hp > player.maxHp) {
                        player.hp = player.maxHp;
                    }
                    clearConsole();
                    player.pots--;
                    printHeading("Inventaire | Vous buvez une potion : + " + Color.RED + soin + Color.RESET);
                    System.out.println(" Il vous reste " + player.pots + " potions");
                    anythingToContinue();
                } else {
                    printHeading("Vous n'avez plus ou pas besoin de prendre une potion");
                    anythingToContinue();
                }
            } else {
                // run away
                clearConsole();
                if (Math.random() * 10 + 1 <= (player.dexterity / 2)) {
                    printHeading("Vous avez réussi à fuir de "+ Color.MAGENTA + enemy.name + Color.RESET + " !");
                    anythingToContinue();
                    break;
                } else {
                    int dmgTook = (int) (Math.random() * 5 - 1) + 1;
                    printHeading("Vous n'avez pas réussi à fuir de " + Color.MAGENTA + enemy.name + Color.RESET + "\net vous a infligez " + dmgTook + " dmg.");
                    player.hp = player.hp - dmgTook;
                    if (player.hp <= 0) {
                        playerDied(); // methode at this end of the game
                        break;
                    }
                } 
                anythingToContinue();
            }
        }
    }

    // printing the main menu
    public static void printMenu() {
        clearConsole();
        printHeading(Color.BLUE_BOLD_BRIGHT + "Menu  " + Color.RESET + "  Localisation : " + places[place]);
        System.out.println("Choisisez une action : ");
        printSeperator(50);
        System.out.println("(1) Continuer Votre Aventure");
        System.out.println("(2) Information Personnage");
        System.err.println("(3) Level Up");
        System.out.println("(4) Partir de la partie");
    }

    // final battle
    public static void finalBattle() {
        battle(new Enemy("Le Roi des Démon", 300, player.Numlevel));
        Story.printEnd(player);
        isRunning = false;
    }

    // method call if player died
    public static void playerDied() {
        clearConsole();
        printHeading("MORT(E) | Vous êtes Mort");
        anythingToContinue();
        characterInfo();
        printHeading("Un notre Héro(e) se relèvera un jour, peut-être");
        isRunning = false;
    }

    // main game loop
    public static void gameLoop() {
        while (isRunning) {
            printMenu();
            int input = readInt("-> ", 4);
            if (input == 1)
                continueJourney();
            else if (input == 2)
                characterInfo();
            else if (input == 3)
                levelUp();
            else
                isRunning = false;
        }
    }

}

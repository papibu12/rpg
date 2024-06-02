package src;
import java.util.Scanner;

public class GameLogic {
    static Scanner scanner = new Scanner(System.in);

    static Player player;
    static Weapon w;

    public static boolean isRunning;

    // random encounters
    public static String[] encounters = { "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle", "Battle",
            "Recluse", "Recluse", "Shop", "Shop", "Rest", "Rest", "Rest", "Forkrugs", "Battle", "Battle"
    };

    // enemy names
    public static String[] enemies = { "Squelette", "rat géant", "Sorcière du claire Lune", "Succube",
            "Golem de Crystalle", "Démon", "méduse", "pyromancien", "papillon solaire", "chevalier corrompue",
            "Chevalier Noir", "géant"
    };

    // Story elements
    public static int place = 0, act = 1;
    public static String[] places = { "Everlasting Mountaing", "Haunted Landline" };

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
        printSeperator(40);
        printSeperator(40);
        System.out.println("Title of the Game");
        printSeperator(40);
        printSeperator(40);
        System.out.println("Text RPG by Papibu");
        printSeperator(40);
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

        // set player classRole

        // print story intro
        Story.printIntro();

        // create a new object with the name
        player = new Player(name, 0, 0, 0, 0, 1);

        if(player.vitality >= 12){
            w = new Sword(name, 5, 1);
        }
        if(player.faith >= 10){
            w = new Mace(name, 5, 1);
        }
        if(player.dexterity >= 12){
            w = 
            new Axe(name, 5, 1);
        }
        if(player.intelligence >= 12){
            w = new Stick(name, 5, 2);
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
        } else {
            shop();
        }
    }

    // methode to continue the journey (more next part)
    public static void continueJourney() {
        // check if act must be increased
        checkAct();
        // check if game isn't in last act
        if (act != 4)
            randomEncounter();
    }

    // printing out the most important information about player character
    public static void characterInfo() {
        clearConsole();
        printHeading(
                Color.BLUE_BOLD_BRIGHT + "Information Personnage   " + Color.RESET + player.name
                        + "\nLevel : " + player.Numlevel);
        System.out
                .println("Vie : " + Color.RED_BRIGHT + player.hp + Color.RESET + "/"
                        + Color.RED_BRIGHT
                        + player.maxHp
                        + Color.RESET);
        System.out.println("Expérience     : " + player.xp);
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
        System.out.println("Potion de Soin : " + player.pots + "\t Gold : " + Color.YELLOW_BRIGHT + player.gold + Color.RESET);
        System.out.println("Feu de Camps : " + player.restleft);
        System.out.println(w.toString());
        anythingToContinue();
    }

    // lvl up stats
    public static void levelUp() {
        clearConsole();
        printHeading(Color.BLUE_BOLD_BRIGHT + "LevelUp  " + Color.RESET + "|");
        if (player.xp >= player.xpRequire) {
            System.out.println("Choissisez une statistique à augmenter ");
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
                    player.maxHp = 3 * player.vitality;
                    player.hp += 3;
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
            player.xpRequire = (player.xpRequire + (int) (Math.random() * 5) + 1);
            player.Numlevel += 1;
        } else {
            System.out.println("Vous ne pouvez pas Level Up");
            anythingToContinue();
        }
    }

    // shopping /encountering
    public static void shop() {

        clearConsole();
        printHeading("Vous rencontrez un " + Color.GREEN +"Marchand" + Color.RESET);
        int pricePotion = (int) (Math.random() * (10 + player.pots * 2) + 5 + player.pots);
        int priceRepos = (int) (Math.random() * (10 + player.restleft * 3) + 10 + player.restleft);

        System.out.println("- Bénédiction divine : " + Color.YELLOW + pricePotion + Color.RESET +" gold.");
        System.out.println("- Feu de Camps : " + Color.YELLOW + priceRepos + Color.RESET + " gold.");
        System.out.println("\n- Vous avez " + Color.YELLOW + player.gold + Color.RESET +" gold");
        printSeperator(20);
        System.out.println("Que voulez-vous acheter ?\n(1) Potion\n(2) Feu de Camps \n(3) Rien");
        int input = readInt("-> ", 4);
        if (input == 1) {
            clearConsole();
            if (player.gold >= pricePotion) {
                printHeading("Vous avez acheter une bénédiction divine \n-" + pricePotion + " gold");
                player.pots++;
                player.gold -= pricePotion;
            } else  {
                printHeading("Vous n'avez pas assez.");
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
                printHeading("Vous n'avez pas assez.");
                anythingToContinue();
            }
        } 
        if (input == 3){
            clearConsole();
            printHeading("Ah, revenez me voir...\nAvec des pièces la prochaine fois...\nah ah ah...");
            anythingToContinue();
        }
        if (input == 4 && player.dexterity >= 12){
            if(Math.random() * 10 + 1 <= player.dexterity / 2){
                int steal =+ (int) (Math.random() * pricePotion + priceRepos) + 5;
                player.gold =+ steal;
                clearConsole();
                printHeading("Vous avez volé " + Color.YELLOW + steal + Color.RESET + " gold." );
                anythingToContinue();
            } 
        }
        
    }

    public static void recluse() {
        clearConsole();
        if (player.Offéria == false) {
            printHeading("Vous rencontrez une recluse");
            if (player.faith >= 10) {
                System.out.println(
                        "Je le sens, la foi, elle vous guide.\nJe me nomme Offéria la recluse de dame Aona.\n Avez-vous besoin de mon aide ?");
                System.out.println("(1) oui\n(2) non\n(3) parler");
                int input_1 = readInt("-> ", 3);
                if (input_1 == 1) {
                    clearConsole();
                    System.out.println("Voulez-vous les faveurs de la Déesse de la fertilité Aona ?");
                    System.out.println("(1) oui\n(2) non");
                    int input_2 = readInt("-> ", 2);
                    if (input_2 == 1) {
                        clearConsole();
                        printHeading(Color.CYAN +"Offéria," + Color.RESET +" Recluse de Aona");
                        System.out.println("Vous avez obtnue la bénédiction de Aona.");
                        player.xp += 5 + player.Numlevel;
                        player.Offéria = true;
                    } else {
                        clearConsole();
                        printHeading(Color.CYAN +"Offéria," + Color.RESET +" Recluse de Aona");
                        System.out.println(
                                "Entendu, si vous avez besoin de mon aide, Aona \nnous rapprochera une fois de plus");
                        anythingToContinue();
                        player.Offéria = true;
                    }
                } else if (input_1 == 3) {
                    clearConsole();
                    printHeading(Color.CYAN +"Offéria," + Color.RESET + " Recluse de Aona");
                    System.out.println(
                            "Je ne suis qu'une simple recluse venant de Carith.\nDepuis j'ai jurée fidelité à la Déeese Aona et \nerre sur les routes en attendant un élu.");
                    anythingToContinue();
                    recluse();
                } else {
                    printHeading(Color.CYAN +"Offéria," + Color.RESET +" Recluse de Aona");
                    System.out.println(
                            "Entendu, si vous avez besoin de mon aide, Aona \nnous rapprochera une fois de plus");
                    anythingToContinue();
                    player.Offéria = true;
                }
            } else {
                System.out.println(
                        " ...\nOh! je ne vous ai pas vue.\nJe suis Offéria, Prêtresse aveugle de Aona\nJe suis sûre que nos chemin se recroiseront");
                anythingToContinue();
                player.Offéria = true;
            }
        } else {
            if (player.faith >= 10) {
                printHeading(Color.CYAN +"Offéria," + Color.RESET +" Recluse de Aona");
                System.out.println("Une fois de plus on se recroise.");
                System.out.println("(1) oui\n(2) non\n(3) parler");
                int input_1 = readInt("-> ", 3);
                if (input_1 == 1) {
                    clearConsole();
                    System.out.println("Voulez-vous les faveurs de la Déesse de la fertilité Aona ?");
                    System.out.println("(1) oui\n(2) non");
                    int input_2 = readInt("-> ", 2);
                    if (input_2 == 1) {
                        clearConsole();
                        printHeading(Color.CYAN +"Offéria," + Color.RESET +" Recluse de Aona");
                        System.out.println("Vous avez obtnue la bénédiction de Aona.");
                        player.xp += 7;
                    } else {
                        printHeading(Color.CYAN +"Offéria," + Color.RESET +" Recluse de Aona");
                        System.out.println(
                                "Entendu, si vous avez besoin de mon aide, Aona \nnous rapprochera une fois de plus");
                        anythingToContinue();
                    }
                } else if (input_1 == 3) {
                    clearConsole();
                    printHeading(Color.CYAN +"Offéria," + Color.RESET +" Recluse de Aona");
                    System.out.println(
                            "Je ne sais pas pourquoi mais j'ai l'impresson que vous,\nvous pourriez peut-être être l'élu que j'attendais ?");
                    anythingToContinue();
                    recluse();
                } else {
                    printHeading(Color.CYAN +"Offéria," + Color.RESET +" Recluse de Aona");
                    System.out.println(
                            "Entendu, si vous avez besoin de mon aide, Aona \nnous rapprochera une fois de plus");
                    anythingToContinue();
                }
            } else {
                printHeading(Color.CYAN +"Offéria," + Color.RESET +" Recluse de Aona");
                System.out.println("Vous ici...\nAona... Vous on se recroisera");
                anythingToContinue();
            }
        }
    }

    public static void forkrugs() {
        clearConsole();
        printHeading("Vous rencontrez une créature étrange resemblant à un four sur patte");
        System.out.println("...\n(1) la tuer\n(2) commercer\n(3) partir");
        int input1 = readInt("-> ", 3);
        if (input1 == 1) {
            randomBattle();
        } else if (input1 == 2) {
            clearConsole();
            int price = (int) (Math.random() * (10 + player.pots * 1.5) + 10 + player.pots);
            System.out.println("- arme +1 : " + price + " gold.");
            System.out.println(Color.YELLOW +""+player.gold +  Color.RESET + " gold");
            printSeperator(20);
            System.out.println(" grug grugh ?\n(1) oui\n(2) non");
            int input2 = readInt("-> ", 2);
            if (input2 == 1) {
                clearConsole();
                if (player.gold >= price) {
                    printHeading("Vous avez arme + 1 \n-" + price + " gold");
                    w.setBaseDmg(2 + w.getBaseWeapon());
                } else {
                    printHeading("Vous n'avez pas assez.");
                    anythingToContinue();
                }
            }
        } else {
            anythingToContinue();
        }

    }

    // takin a rest
    public static void takeRest() {
        clearConsole();
        if (player.restleft >= 1) {
            printHeading("Voulez-vous vous reposer ? (" + player.restleft + "repot(s) restant).");
            System.out.println("Pv : " + player.hp +"/"+ player.maxHp);
            System.out.println("(1) oui\n(2) non");
            int input = readInt("-> ", 2);
            if (input == 1) {
                clearConsole();
                printHeading("Vous vous êtes reposé(e)");
                if (player.hp < player.maxHp) {
                    int hpRestored = (int) ((Math.random() * (player.vitality / 2) + player.Numlevel  + 10) + 10);
                    player.hp += hpRestored;
                    if (player.hp >= player.maxHp)
                        player.hp = player.maxHp;
                    System.out.println("Et vous vous êtes restauré(e) " + Color.RED + hpRestored + Color.RESET +" Hp.");
                    System.out.println(player.hp + "/" + player.maxHp + " Hp.");
                    player.restleft--;
                }
                anythingToContinue();

            } else {
                clearConsole();
                System.out.println("Vous ne vous sentez pas fatiguer");
                anythingToContinue();
            }

        }
    }

    // creating a random battle
    public static void randomBattle() {
        clearConsole();
        printHeading("Le Mal vous a Trouvé, Vous allez vous Battre !");
        anythingToContinue();
        // creatting a new enemy whit random name
        battle(new Enemy(enemies[(int) (Math.random() * enemies.length)], player.xp, player.Numlevel));
    }

    // the main Battle method
    public static void battle(Enemy enemy) {
        // main loop
        while (true) {
            clearConsole();
            printHeading(Color.MAGENTA + enemy.name + Color.RESET + "\nHp : " + Color.RED + enemy.hp + Color.RESET + "/" + Color.RED + enemy.maxHp
                    + Color.RESET);
            printHeading(Color.GREEN_BRIGHT + player.name + Color.RESET + "\nHp : " + Color.RED + player.hp
                    + Color.RESET + "/" + Color.RED
                    + player.maxHp + Color.RESET);
            System.out.println("Choisisez une action");
            printSeperator(20);
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
                printHeading("Combat");
                System.out.println("Vous avez infligé " + dmg + " dmg à " + Color.MAGENTA + enemy.name + Color.RESET + ".");
                printSeperator(40);
                printSeperator(40);
                System.out.println((Color.MAGENTA + enemy.name + Color.RESET+ " vous à infligé " + dmgTook + " dmg."));
                anythingToContinue();
                // check if player is alive
                if (player.hp <= 0) {
                    playerDied(); // methode at this end of the game
                    break;
                } else if (enemy.hp <= 0) {
                    clearConsole();
                    printHeading(Color.YELLOW_BOLD_BRIGHT + "Vous avez Triomphé" + Color.RESET);
                    player.xp += enemy.xp;
                    System.out.println("+ " + enemy.xp + "xp !");
                    // random
                    boolean addRest = (Math.random() * 5 + 1 <= 2.25);
                    int goldEarned = (int) (Math.random() * enemy.xp);
                    if (addRest) {
                        player.restleft++;
                        System.out.println("Vous avez obtenu une chance supplémentaire de pouvoir vous reposez !");
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
                    int soin = (int) (Math.random() * 20 ) + 5;
                    player.hp += soin;
                    if (player.hp > player.maxHp) {
                        player.hp = player.maxHp;
                    }
                    clearConsole();
                    player.pots--;
                    printHeading("Vous avez bu une potion: + " + Color.RED + soin + Color.RESET);
                    anythingToContinue();
                } else {
                    printHeading("Vous n'avez plus ou pas besoin de prendre une potion");
                    anythingToContinue();
                }
            } else {
                // run away
                clearConsole();
                if (Math.random() * 10 + 1 <= player.dexterity / 2) {
                    printHeading("Vous avez réussi à fuir de "+ Color.MAGENTA + enemy.name + Color.RESET + " !");
                    anythingToContinue();
                    break;
                } else {
                    int dmgTook = enemy.attack(w);
                    printHeading("Vous n'avez pas réussi à fuir de " + Color.MAGENTA + enemy.name + Color.RESET + "\nEt vous a infligez " + dmgTook + " dmg.");
                    player.hp =- dmgTook;
                    if (player.hp <= 0) {
                        playerDied();
                    } else{
                        anythingToContinue();
                    }
                    // check player alive
                    
                } 
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
        printHeading("Vous êtes Mort");
        printHeading("Un notre Héro se relèvera un jour");
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

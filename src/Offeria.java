package src;


public class Offeria {
    public static void firstEncounter(Player p){
        GameLogic.clearConsole();
        GameLogic.printHeading("??? | Vous rencontrez une recluse");
        System.out.println("Oh !! vous m'avez surpris, qui êtes-vous ?\nsi vous me cherchiez je ne rempondrez à aucune\ninterrogation");
        GameLogic.printSeperator(50);
        System.out.println("(1) lui dire votre nom\n(2) non");
        int input = GameLogic.readInt("-> ", 2);
        GameLogic.clearConsole();
        if(input == 1){
            GameLogic.printHeading("??? |");
            System.out.println("Et bien "  + Color.GREEN + p.name + Color.RESET +", je me nomme Offéria\net je suis une servante de la Déeese de la\nfertilité, Aona.\nQue me voulez-vous me demander ?");
            GameLogic.printSeperator(50);
            System.out.println("(1) Lui demander de l'aide\n(2) parler\n(3) partir");
            input = GameLogic.readInt("-> ", 3);
            GameLogic.clearConsole();
            if(input == 1){
                GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
                System.out.println("Voulez-vous reçevoir les faveurs d'Aona ?");
                GameLogic.printSeperator(50);
                System.out.println("(1) Oui\n(2) non");
                input = GameLogic.readInt("-> ", 2);
                GameLogic.clearConsole();
                if(input == 1 && p.faith >= 10){
                    GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
                    System.out.println("Benedicat tibi.");
                    int benediction = (p.faith / 8) + p.Numlevel;
                    if(benediction <= 1){
                        p.xpRequire = p.xpRequire -2;
                    } else {
                        p.xp += benediction;
                    }
                } else {
                    GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
                    System.out.println("Malheureusement votre passion n'est pas\nassez grande.");
                    GameLogic.anythingToContinue();
                    encounter(p);
                }
            }
            else if(input == 2){
                GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
                System.out.println("Ma quête ? et bien elle est d'attendre la venu,\nd'un jour, de l'élu de ma Déeese.");
                GameLogic.anythingToContinue();
                encounter(p);                
            }
        } else {
            GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
            System.out.println("Bon voyage aventurier.\nNe vous enfaites pas nos route se recroiseront\ngrâce à Aona.");
            GameLogic.anythingToContinue();
        }
        p.Offéria = true;
        p.OffériaEncounter = p.OffériaEncounter + 1;
    }

    public static void encounter(Player p){
        GameLogic.clearConsole();
        GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
        System.out.println("(1) Lui demander de l'aide\n(2) parler\n(3) partir");
        int input = GameLogic.readInt("-> ", 3);
        GameLogic.clearConsole();
            if(input == 1){
                GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
                System.out.println("Voulez-vous reçevoir les faveurs d'Aona ?");
                GameLogic.printSeperator(50);
                System.out.println("(1) Oui\n(2) non");
                input = GameLogic.readInt("-> ", 2);
                GameLogic.clearConsole();
                if(input == 1 && p.faith >= 10){
                    GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
                    System.out.println("Benedicat tibi.");
                    int benediction = (p.faith / 8) + p.Numlevel;
                    if(benediction <= 1){
                        p.xpRequire = p.xpRequire -2;
                    } else {
                        p.xp += benediction;
                    }
                } else {
                    GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
                    System.out.println("Malheureusement votre passion n'est pas\nassez grande.");
                    GameLogic.anythingToContinue();
                    encounter(p); 
                }  
            }
            else if(input == 2){
                if(p.OffériaEncounter <= 1){
                    GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
                    System.out.println("Ma quête ? et bien elle est d'attendre la venu,\nd'un jour, de l'élu de ma Déeese.");         
                } else {
                    GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
                    System.out.println("Peut-être que c'est vous mon élu\n...");           
                }
                GameLogic.anythingToContinue();
                p.OffériaEncounter = p.OffériaEncounter + 1;
                encounter(p);    
            } else {
            GameLogic.printHeading(Color.CYAN +"Offéria" + Color.RESET +" | Recluse de Aona");
            System.out.println("Bon voyage aventurier.\nNe vous enfaites pas nos route se recroiseront\ngrâce à Aona.");
        }
        GameLogic.anythingToContinue();
        p.OffériaEncounter = p.OffériaEncounter + 1;
    }

}

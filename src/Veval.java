package src;

public class Veval {
    

    public static void firstEncounter(Player p){
        GameLogic.clearConsole();
        GameLogic.printHeading("??? | Vous rencontrez un homme");
        GameLogic.printSeperator(50);
        System.out.println("(1) qui êtes-vous\n(2) partir");
        int input = GameLogic.readInt("-> ", 2);
        GameLogic.clearConsole();
        if(input == 1){
            GameLogic.printHeading("??? |");
            System.out.println("Je me nomme Veval Din, et j'épluche la démonologie\nAuriez-vous un instant à m'accorder ?");
            GameLogic.printSeperator(50);
            System.out.println("(1) oui\n(2) non");
            input = GameLogic.readInt("->", 2);
            GameLogic.clearConsole();
            if(input == 1){
                GameLogic.printHeading(Color.CYAN + "Veval" + Color.RESET + " | démonologiste");
                System.out.println("Je me nomme Veval Din, et j'épluche la démonologie\nAuriez-vous un instant à m'accorder ?");
                GameLogic.printSeperator(50);
            }
        }
    }
}

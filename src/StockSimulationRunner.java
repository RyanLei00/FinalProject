import java.util.*;

public class StockSimulationRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to Ryan's scuffed Stock Market Financial Analysis.");
        System.out.println("Would you like to look at previous stocks or get advice if you should buy stocks? (previous or advice)");
        String option = s.nextLine();
        if(option.equals("previous")) {
            StockMarket stocks = new StockMarket();
            stocks.stockOptions();
            stocks.findStock();
        }
        else if(option.equals("advice")) {
            StockMarket advice = new StockMarket();
            advice.stockOptions();
            advice.adviceStock();
        }
        else{
            System.out.println("Sorry. That is not an option");
        }

    }
}


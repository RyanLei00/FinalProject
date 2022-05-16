import java.util.*;

public class StockSimulationRunner {
    public static void main(String[] args) {
        Person newPerson = new Person("Ryan Lei", 10000);
        StockMarket stocks = new StockMarket();
        System.out.println(newPerson.getMoney());
        System.out.println(newPerson.getName());
        newPerson.save();
        stocks.stockOptions();
        stocks.findStock();
    }
}


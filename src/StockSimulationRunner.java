import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StockSimulationRunner {
    public static void main(String[] args) {
        StockMarket stock = new StockMarket();
        WebAPI web = new WebAPI();
        stock.stockOptions();
        web.findStock();
    }
}


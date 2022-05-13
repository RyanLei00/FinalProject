import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

//https://polygon.io/

public class StockMarket {

    private ArrayList<String> Stocks;

    public StockMarket(){
        Stocks.add("AMAZ");
        Stocks.add("APPL");
        Stocks.add("TSLA");
        Stocks.add("BTC");
        Stocks.add("ETH");
    }

    public void stockOptions(){
        for(int i = 0; i < Stocks.size(); i++) {
            if(i == Stocks.size() - 1) {
                System.out.print(Stocks.get(i));
            }
            else{
                System.out.print(Stocks.get(i) + ", ");
            }
        }
    }
}

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.format.DateTimeFormatter;

//https://polygon.io/

public class StockMarket {

    private ArrayList<String> Stocks;
    private String APIkey;
    private String stockName;

    public StockMarket(){
        Stocks = new ArrayList<String>();
        Stocks.add("AMZN");
        Stocks.add("AAPL");
        Stocks.add("TSLA");
        Stocks.add("BTC");
        Stocks.add("ETH");
        APIkey = "Dgd8uy3CevzAlTWZJ79VyKfDnq7Q6J_2";
    }

    public void stockOptions(){
        System.out.print("Stock Options: ");
        for(int i = 0; i < Stocks.size(); i++) {
            if(i == Stocks.size() - 1) {
                System.out.print(Stocks.get(i) + "\n");
            }
            else{
                System.out.print(Stocks.get(i) + ", ");
            }
        }
    }

    public void findStock() {
        Scanner s = new Scanner(System.in);
        System.out.print("Please choose a stock: ");
        this.stockName = s.nextLine();
        stockName = stockName.toUpperCase();
        System.out.print("How long do you want to run the simulation (ie. day, month, year): ");
        String timeSpan = s.nextLine();
        System.out.print("When do you want to start the simulation (ie. 2021-07-22): ");
        String date = s.nextLine();

        String urlStock = "https://api.polygon.io/v2/aggs/ticker/" + stockName + "/range/1/"+ timeSpan + "/" + date + "/" + date + "?apiKey=" + APIkey;
        makeAPICall(urlStock);
    }

    public void adviceStock(){
        Scanner s = new Scanner(System.in);
        System.out.print("Please choose a stock: ");
        this.stockName = s.nextLine();
        stockName = stockName.toUpperCase();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        System.out.println("Yesterday's date: " + dtf.format(yesterday));

        String urlStock = "https://api.polygon.io/v2/aggs/ticker/" + stockName + "/range/1/day/" + yesterday + "/" + yesterday + "?apiKey=" + APIkey;
        makeAPICall(urlStock);

        LocalDate quarterMonth = today.minusMonths(3);
        System.out.println("3 Months Ago date: " + dtf.format(quarterMonth));

        urlStock = "https://api.polygon.io/v2/aggs/ticker/" + stockName + "/range/1/quarter/" + quarterMonth + "/" + yesterday + "?apiKey=" + APIkey;
        makeAPICall(urlStock);
    }




    public void makeAPICall(String url)
    {
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest.Builder builder = HttpRequest.newBuilder();
            builder.uri(myUri); // sets the URI
            HttpRequest request = builder.build();

            // it is common to see this in one line, like this:
            //HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            parseJSON(response.body());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void parseJSON(String json)
    {
        JSONObject jsonObj = new JSONObject(json);
        JSONArray stockList = jsonObj.getJSONArray("results");

        for (int i = 0; i < stockList.length(); i++)
        {
            JSONObject stockObj = stockList.getJSONObject(i);
            double opening = stockObj.getDouble("o");
            double closing = stockObj.getDouble("c");
            double highest = stockObj.getDouble("h");
            double lowest = stockObj.getDouble("l");

            Stock stock = new Stock(stockName, opening, closing, highest, lowest);
            System.out.println(stock.toString());
        }
    }

}

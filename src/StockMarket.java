import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

//https://polygon.io/

public class StockMarket {

    private ArrayList<String> Stocks;
    private String APIkey;

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
        String stockName = s.nextLine();
        System.out.print("How long do you want to run the simulation (ie. day, hour, week, month, year): ");
        String timeSpan = s.nextLine();
        System.out.print("When do you want to start the simulation (ie. 2021-07-22): ");
        String from = s.nextLine();
        System.out.print("When do you want to end the simulation (ie. 2021-07-22): ");
        String to = s.nextLine();


        String urlStock = "https://api.polygon.io/v2/aggs/ticker/" + stockName + "/range/1/"+ timeSpan + "/" + from + "/" + to + "?apiKey=" + APIkey;
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

            //System.out.println(response);
            //System.out.println(response.request());
            //System.out.println(response.statusCode());
            //System.out.println(response.headers());
            System.out.println(response.body());

            // COMING SOON!
            //parse(response.body());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

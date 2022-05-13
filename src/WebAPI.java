import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class WebAPI {

    private String APIkey;

    public WebAPI(){
        APIkey = "Dgd8uy3CevzAlTWZJ79VyKfDnq7Q6J_2";
    }

    public void findStock() {
        Scanner s = new Scanner(System.in);
        System.out.print("Please choose a stock: ");
        String stockName = s.nextLine();
        System.out.print("How long do you want to run the simulation (ie. days, hours, weeks, months, years): ");
        String time = s.nextLine();
        System.out.print("When do you want to start the simulation (has to be a past date): ");
        String startTime = s.nextLine();
        System.out.print("When do you want to end the simulation (has to be a past date): ");
        String endTime = s.nextLine();


        String urlStock = "https://api.polygon.io/v2/aggs/ticker/" + stockName + "/range/1/"+ time + "/" +startTime + "/" + endTime + "?apiKey=" + APIkey;
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

package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;



public class Eaties {

    private Places places;
    private Gson gson;
    String jsonRequest;
    HttpRequest postRequest;


    public Eaties(String text) throws Exception{
        resetFields(text);
    }

    public HttpRequest makePostRequest(String jsonRequest) throws Exception{
        HttpRequest postRequest = HttpRequest.newBuilder()
            .uri(new URI("https://places.googleapis.com/v1/places:searchText"))
            .header("X-Goog-Api-Key", "insert your own Google Maps API key")
            .header("X-Goog-FieldMask", "places.displayName.text,places.formattedAddress,places.rating,places.priceLevel,places.goodForWatchingSports")
            .POST(BodyPublishers.ofString(jsonRequest))
            .build();
        return postRequest;
    }

    public void run() throws Exception{
        jsonRequest = gson.toJson(places);
        postRequest = makePostRequest(jsonRequest);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
        places = gson.fromJson(postResponse.body(), Places.class);
    }

    public void resetFields(String text){
        places = new Places();
        places.setTextQuery(text);
        gson = new Gson();
    }
    public static void main(String[] args) throws Exception{
        Eaties eaties = new Eaties("Pizza");
        eaties.run();
        for (Place p : eaties.places.places){
        
            System.out.println(p.getFormattedAddress().toString());
        }
        
    }
    
}

package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        HttpRequest request = null;
        HttpResponse<String> response = null;
        List<listJsion> products = null;
        Gson gson = new Gson();
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i1 = 1; i1 < 10; i1++) {



                for (int i = 1; i < 9999; i++) {
                    if (!integers.contains(i)) {
                try {
                    request = HttpRequest.newBuilder()
                            .uri(new URI("https://catalog.wb.ru/catalog/electronic"+i1+"/catalog?appType=1&couponsGeo=2,12,3,18,21&curr=rub&dest=-1029256,-81995,-1235082,123585487&emp=0&lang=ru&locale=ru&page=1&pricemarginCoeff=1.0&reg=0&regions=68,64,83,4,38,80,33,70,82,86,30,69,1,48,22,66,31,40&sort=priceup&spp=0&subject=" + i))
                            .GET()
                            .build();
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                HttpClient client = HttpClient.newBuilder()
                        .version(HttpClient.Version.HTTP_1_1)
                        .followRedirects(HttpClient.Redirect.NORMAL)
                        .connectTimeout(Duration.ofSeconds(20))
                        .build();
                response = null;
                try {
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {

                    json1 json1 = gson.fromJson(response.body(), json1.class);
                    products = json1.data.getProducts();
                    if (products.size() != 0) {


                            if (Integer.parseInt(products.get(0).getSalePriceU()) < 1000_00) {
                                try {
                                    System.out.println(i1 + " - " + i + " - " + products.get(0).getName() + " | " + products.get(1).getName());
                                } catch (Exception e) {
                                    System.out.println(i1 + " - " + i + " - " + products.get(0).getName());
                                }
                            }
                            integers.add(i);
                        }
                } catch (JsonSyntaxException ex) {
                }

                }

            }
        }
    }
}
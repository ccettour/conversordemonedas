package com.ccettour.conversordemonedas.calculos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaIndice {
    public double getIndice(String monedaBase, String monedaTarget) {


        String API_KEY = "ca3ab6c81e749ccb3648b122";
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + monedaBase + "/" + monedaTarget);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response;

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
            return jsonObject.get("conversion_rate").getAsDouble();

        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener el índice de conversión");
        }
    }
}

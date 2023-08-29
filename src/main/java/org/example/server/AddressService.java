package org.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AddressService implements RepositoryHTTPAddress {
    @Override
    public Address searchAddress(String CEP) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://viacep.com.br/ws/"+CEP+"/json/"))
                .build();

        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.body(), Address.class);
    }
}

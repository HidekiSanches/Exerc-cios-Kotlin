package br.com.teste.testegames.service

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi {
    fun buscarJogo(id: String): String? {
        val client: HttpClient = HttpClient.newHttpClient();
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=$id"))
            .build();
        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        return response.body()
    }
}
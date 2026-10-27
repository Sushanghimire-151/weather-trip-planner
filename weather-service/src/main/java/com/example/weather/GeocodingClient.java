package com.example.weather;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GeocodingClient {

    private final RestClient client;

    public GeocodingClient() {
        SimpleClientHttpRequestFactory requestFactory =
                new SimpleClientHttpRequestFactory();

        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(5000);

        this.client = RestClient.builder()
                .baseUrl("https://geocoding-api.open-meteo.com")
                .requestFactory(requestFactory)
                .build();
    }

    public GeocodingResponse search(String name) {
        return client.get()
                .uri(builder -> builder
                        .path("/v1/search")
                        .queryParam("name", "{name}")
                        .queryParam("count", 5)
                        .queryParam("language", "en")
                        .queryParam("format", "json")
                        .build(name))
                .retrieve()
                .body(GeocodingResponse.class);
    }
}


/*
get()                         Choose HTTP GET
uri(...)                      Build the URL and parameters
retrieve()                    Retrieve the response
body(GeocodingResponse.class)  Convert JSON into a Java object

The timeout values are in milliseconds:
- Connect timeout: Up to three seconds to establish the connection.
- Read timeout: Up to five seconds waiting for a read operation.
The URI builder encodes the supplied city name safely, including spaces.
RestClient is Spring’s synchronous HTTP client. Here we create it explicitly,
so this example does not depend on an auto-configured RestClient.Builder bean.
Spring REST client documentation
 */
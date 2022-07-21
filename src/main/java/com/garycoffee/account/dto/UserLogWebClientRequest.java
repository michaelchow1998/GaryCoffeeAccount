package com.garycoffee.account.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserLogWebClientRequest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public String createUserLog(RequestLogUser req){

        String uri = "https://gary-coffee-log.herokuapp.com/api/v1/user-log";
        String message = webClientBuilder.build()
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(req), String.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return message;
    }


}

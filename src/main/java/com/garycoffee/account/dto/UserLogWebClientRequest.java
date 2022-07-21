package com.garycoffee.account.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserLogWebClientRequest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public void createUserLog(RequestLogUser req){
        log.info("log: {}, {}, {}", req.getPhone(),req.getTransactionType(),req.getMessage());

        String uri = "https://gary-coffee-log.herokuapp.com/api/v1/user-log";
//            String uri ="http://localhost:8080/api/v1/user-log";
            webClientBuilder.build()
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(req), RequestLogUser.class);

    }


}

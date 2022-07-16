package com.garycoffee.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("account")
public class Account {

    @Id
    private String id;

    private String username;

    @Indexed(unique = true)
    private String phone;

    private Integer AccountBalance;

    private Integer IntegralBalance;
}

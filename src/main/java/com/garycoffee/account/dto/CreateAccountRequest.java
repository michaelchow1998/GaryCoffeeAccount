package com.garycoffee.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {

    private String username;
    private String phone;
}

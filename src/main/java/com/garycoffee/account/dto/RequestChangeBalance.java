package com.garycoffee.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestChangeBalance {


    @NotBlank
    private String phone;

    @NotNull
    @JsonProperty("amount")
    Integer Amount;
}

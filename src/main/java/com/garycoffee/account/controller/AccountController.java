package com.garycoffee.account.controller;

import com.garycoffee.account.dto.CreateAccountRequest;
import com.garycoffee.account.dto.RequestChangeBalance;
import com.garycoffee.account.model.Account;
import com.garycoffee.account.repo.AccountRepo;
import com.garycoffee.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/accounts")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest req){
        Account account = accountService.createAccount(req);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/accounts").toUriString());
        return ResponseEntity.created(uri).body(account);
    }

    @GetMapping("/{phone}")
    public ResponseEntity<Account> fetchAccountByUserName(@PathVariable String phone){
        Account account = accountService.getAccountBalance(phone);
        return ResponseEntity.ok().body(account);
    }

    @GetMapping("/exists/{phone}")
    public ResponseEntity<Boolean> fetchAccountExitsByPhone(@PathVariable String phone){
        Boolean exists = accountService.existsAccountByPhone(phone);
        return ResponseEntity.ok().body(exists);
    }

    //PUT - ADD Balance to User
    @PutMapping("/addBalance")
    public ResponseEntity<Account> addBalance
            (@RequestBody @Valid RequestChangeBalance req){
        Account account = accountService.addBalance(req.getPhone(), req.getAmount());
        return ResponseEntity.ok().body(account);
    }

    //PUT - REDUCE Balance to User
    @PutMapping("/reduceBalance")
    public ResponseEntity<Account> reduceBalance
    (@RequestBody @Valid RequestChangeBalance req){
        Account account = accountService.reduceBalance(req.getPhone(), req.getAmount());
        return ResponseEntity.ok().body(account);
    }

    //PUT - ADD Integral to User
    @PutMapping("/addIntegral")
    public ResponseEntity<Account> addIntegral
            (@RequestBody @Valid RequestChangeBalance req){
        Account account = accountService.addIntegral(req.getPhone(), req.getAmount());
        return ResponseEntity.ok().body(account);
    }

    //PUT - REDUCE Integral to User
    @PutMapping("/reduceIntegral")
    public ResponseEntity<Account> reduceIntegral
            (@RequestBody @Valid RequestChangeBalance req){
        Account account = accountService.reduceIntegral(req.getPhone(), req.getAmount());
        return ResponseEntity.ok().body(account);
    }
}

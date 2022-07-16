package com.garycoffee.account.service;

import com.garycoffee.account.dto.CreateAccountRequest;
import com.garycoffee.account.model.Account;
import com.garycoffee.account.repo.AccountRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public Account createAccount(CreateAccountRequest req){
        Account account = new Account();
        account.setUsername(req.getUsername());
        account.setPhone(req.getPhone());
        account.setAccountBalance(100);
        account.setIntegralBalance(0);

        return accountRepo.insert(account);
    }


    public Account getAccount(String phone){
       Account account = accountRepo.getAccountByPhone(phone);
       if(account != null){
           return account;
       }else{
           return null;
       }
    }

    public Account addBalance(String phone, Integer addAmount){
        Account targetAccount = accountRepo.getAccountByPhone(phone);
        Integer currentBalance = targetAccount.getAccountBalance();
        Integer AfterCountBalance = currentBalance + addAmount;
        targetAccount.setAccountBalance(AfterCountBalance);
        accountRepo.save(targetAccount);
        return targetAccount;
    }

    public Account reduceBalance(String phone, Integer addAmount){
        Account targetAccount = accountRepo.getAccountByPhone(phone);
        Integer currentBalance = targetAccount.getAccountBalance();
        Integer AfterCountBalance = currentBalance - addAmount;
        if(AfterCountBalance>=0){
            targetAccount.setAccountBalance(AfterCountBalance);
            accountRepo.save(targetAccount);
            return targetAccount;
        }else{
            throw new RuntimeException("Your Balance have not enough money.");
        }

    }
}

package com.garycoffee.account.service;

import com.garycoffee.account.dto.CreateAccountRequest;
import com.garycoffee.account.dto.RequestLogUser;
import com.garycoffee.account.dto.TransactionType;
import com.garycoffee.account.dto.UserLogWebClientRequest;
import com.garycoffee.account.model.Account;
import com.garycoffee.account.repo.AccountRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@AllArgsConstructor
@Service
@Slf4j
public class AccountService {

    @Resource
    private AccountRepo accountRepo;

    @Resource
    private UserLogWebClientRequest userLogWebClientRequest;

    public Account createAccount(CreateAccountRequest req){
        Account account = new Account();
        account.setUsername(req.getUsername());
        account.setPhone(req.getPhone());
        account.setAccountBalance(100);
        account.setIntegralBalance(0);

        return accountRepo.insert(account);
    }


    public Account getAccountBalance(String phone){
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
        targetAccount.setIntegralBalance(targetAccount.getIntegralBalance()+addAmount/10);
        accountRepo.save(targetAccount);

        String logMessage = userLogWebClientRequest.createUserLog(
                new RequestLogUser(
                        phone,
                        TransactionType.Increase,
                        phone + " Account Balance: increase " + addAmount + " $"
                ));
        log.info("{}",logMessage);

        return targetAccount;
    }

    public Account reduceBalance(String phone, Integer addAmount){
        Account targetAccount = accountRepo.getAccountByPhone(phone);
        Integer currentBalance = targetAccount.getAccountBalance();
        Integer AfterCountBalance = currentBalance - addAmount;
        if(AfterCountBalance>=0){
            targetAccount.setAccountBalance(AfterCountBalance);
            accountRepo.save(targetAccount);

            String logMessage = userLogWebClientRequest.createUserLog(
                    new RequestLogUser(
                            phone,
                            TransactionType.Reduce,
                            phone + " Account Balance: reduce " + addAmount + " $"
                    ));
            log.info("{}",logMessage);

            return targetAccount;
        }else{
            throw new RuntimeException("Your Balance have not enough money.");
        }

    }

    public Account addIntegral(String phone, Integer addAmount){
        Account targetAccount = accountRepo.getAccountByPhone(phone);
        Integer currentBalance = targetAccount.getIntegralBalance();
        Integer AfterCountBalance = currentBalance + addAmount;
        targetAccount.setIntegralBalance(AfterCountBalance);
        accountRepo.save(targetAccount);
        return targetAccount;
    }

    public Account reduceIntegral(String phone, Integer addAmount){
        Account targetAccount = accountRepo.getAccountByPhone(phone);
        Integer currentBalance = targetAccount.getIntegralBalance();
        Integer AfterCountBalance = currentBalance - addAmount;
        if(AfterCountBalance>=0){
            targetAccount.setIntegralBalance(AfterCountBalance);
            accountRepo.save(targetAccount);
            return targetAccount;
        }else{
            throw new RuntimeException("Your Balance have not enough Integral.");
        }

    }



}

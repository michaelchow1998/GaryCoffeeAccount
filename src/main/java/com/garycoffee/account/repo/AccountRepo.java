package com.garycoffee.account.repo;

import com.garycoffee.account.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepo extends MongoRepository<Account, String> {

    Account getAccountByUsername(String username);

    Account getAccountByPhone(String phone);

    boolean existsAccountByPhone(String phone);
}

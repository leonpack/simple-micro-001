package com.nathan.accountservice.controller;

import com.nathan.accountservice.client.AsyncService;
import com.nathan.accountservice.model.AccountDTO;
import com.nathan.accountservice.model.MessageDTO;
import com.nathan.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@Slf4j
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final AsyncService asyncService;

    @PostMapping
    public AccountDTO addAccount(@RequestBody AccountDTO account) {
        logger.info("Received new account: {}", account);
        accountService.add(account);
        asyncService.addStatistic("Account created: " + account.getUsername());
        asyncService.sendNotification(account.getUsername(), account.getName());
        logger.info("Account created and processed asynchronously: {}", account);
        return account;
    }

    @GetMapping
    public List<AccountDTO> getAccounts() {
        logger.info("Received request to get all accounts");
        asyncService.addStatistic(String.format("Get all accounts: %d", accountService.getAll().size()));
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        logger.info("Received request to get account by ID: {}", id);
        asyncService.addStatistic(String.format("Get account by ID: %d", id));
        return Optional.of(new ResponseEntity<AccountDTO>(accountService.getOne(id), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
     public void deleteAccount(@PathVariable Long id) {
        logger.info("Received request to delete account by ID: {}", id);
        asyncService.addStatistic(String.format("Delete account by ID: %d", id));
        accountService.delete(id);
    }

    @PutMapping
    public void update(@RequestBody AccountDTO accountDTO) {
        logger.info("Received request to update account: {}", accountDTO);
        asyncService.addStatistic(String.format("Update account: %d", accountDTO.getId()));
        accountService.update(accountDTO);
    }
}

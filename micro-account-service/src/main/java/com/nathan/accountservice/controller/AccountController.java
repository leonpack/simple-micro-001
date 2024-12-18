package com.nathan.accountservice.controller;

import com.nathan.accountservice.client.AsyncService;
import com.nathan.accountservice.model.AccountDTO;
import com.nathan.accountservice.model.MessageDTO;
import com.nathan.accountservice.model.StatisticDTO;
import com.nathan.accountservice.service.AccountService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@Slf4j
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final AsyncService asyncService;

    @PostMapping("/register")
    @PermitAll
    public AccountDTO register(@RequestBody AccountDTO account, @RequestHeader("Authorization") String bearerToken) {
        account.setRoles(new HashSet<String>(Arrays.asList("ROLE_USER")));
        accountService.add(account);

        asyncService.addStatistic("Account created: " + account.getUsername());
        asyncService.sendNotification(account.getUsername(), account.getName());

        return account;
    }

    @PreAuthorize("hasAuthority('SCOPE_read') && hasRole('ADMIN')")
    @PostMapping
    public AccountDTO addAccount(@RequestBody AccountDTO account) {
        logger.info("Received new account: {}", account);
        accountService.add(account);

        asyncService.addStatistic("Account created: " + account.getUsername());
        asyncService.sendNotification(account.getUsername(), account.getName());

        logger.info("Account created and processed asynchronously: {}", account);
        return account;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<AccountDTO> getAccounts() {
        logger.info("Received request to get all accounts");
        asyncService.addStatistic(String.format("Get all accounts: %d", accountService.getAll().size()));
        return accountService.getAll();
    }

    @PreAuthorize("hasAuthority('SCOPE_read') && isAuthenticated()")
    @PostAuthorize("returnObject.body.username == authentication.name || hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        logger.info("Received request to get account by ID: {}", id);
        asyncService.addStatistic(String.format("Get account by ID: %d", id));
        return Optional.of(new ResponseEntity<AccountDTO>(accountService.getOne(id), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('SCOPE_write') && hasRole('ADMIN')")
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

    @GetMapping("/me")
    public Principal me(Principal principal, @RequestHeader("Authorization") String bearerToken) {
        return principal;
    }
}

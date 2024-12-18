package com.nathan.authservice.service;

import com.nathan.authservice.entity.Account;
import com.nathan.authservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException("no user detected");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        account.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });

        return new User(account.getUsername(), account.getPassword(), authorities);
    }
}

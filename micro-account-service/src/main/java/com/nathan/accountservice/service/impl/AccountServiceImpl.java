package com.nathan.accountservice.service.impl;

import com.nathan.accountservice.entity.Account;
import com.nathan.accountservice.model.AccountDTO;
import com.nathan.accountservice.repository.AccountRepository;
import com.nathan.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        accountRepository.save(account);
        accountDTO.setId(account.getId());
    }

    @Override
    public void update(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getId()).orElse(null);
        if(account != null) {
            modelMapper.typeMap(AccountDTO.class, Account.class)
                    .addMappings(modelMapper -> modelMapper.skip(Account::setPassword)).map(accountDTO, account);
            accountRepository.save(account);
        }
    }

    @Override
    public void updatePassword(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getId()).orElse(null);
        if(account!= null) {
            account.setPassword(accountDTO.getPassword());
            accountRepository.save(account);
        }
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountRepository.findAll().stream().map(account -> modelMapper.map(account, AccountDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AccountDTO getOne(Long id) {
        return accountRepository.findById(id).map(account -> modelMapper.map(account, AccountDTO.class)).orElse(null);
    }
}

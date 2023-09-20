package com.github.alekseypetkun.bankaccountapp.service.impl;

import com.github.alekseypetkun.bankaccountapp.dto.*;
import com.github.alekseypetkun.bankaccountapp.entity.Account;
import com.github.alekseypetkun.bankaccountapp.exception.AuthenticationException;
import com.github.alekseypetkun.bankaccountapp.exception.ExistingAccountException;
import com.github.alekseypetkun.bankaccountapp.exception.NotFoundAccountException;
import com.github.alekseypetkun.bankaccountapp.exception.RemoveFromBalanceException;
import com.github.alekseypetkun.bankaccountapp.mapper.AccountMapper;
import com.github.alekseypetkun.bankaccountapp.repository.AccountRepository;
import com.github.alekseypetkun.bankaccountapp.security.PBFDK2PasswordEncoder;
import com.github.alekseypetkun.bankaccountapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Бизнес-логика по работе с аккаунтом
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PBFDK2PasswordEncoder passwordEncoder;

    @Override
    public ResponseAccount addAccount(CreateAccount dto) {

        if (accountRepository.findAccountByUsername(dto.getUsername()) != null) {
            throw new ExistingAccountException(dto.getUsername());
        }

        Account account = accountMapper.map(dto);
        account.setPassword(passwordEncoder.encode(dto.getPassword()));
        account.setBalance(0);
        ResponseAccount newAccount = accountMapper.map(accountRepository.save(account));
        log.info("IN addAccount - account: {} created", account);
        return newAccount;
    }

    @Override
    public ResponseAccount replenishBalance(UpdateBalance dto) {

        Account account = checkAccount(dto.getAccountId());

        account.setBalance(account.getBalance() + dto.getBalance());
        ResponseAccount newAccount = accountMapper.map(accountRepository.save(account));
        log.info("IN updateAccount - account: {} updated", account);
        return newAccount;
    }

    @Override
    public ResponseAccount removeFromBalance(UpdateBalance dto, String password) {

        Account account = checkAccount(dto.getAccountId());

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new AuthenticationException(password);
        }

        if (account.getBalance() - dto.getBalance() < 0) {
            throw new RemoveFromBalanceException(account.getBalance());
        }
        account.setBalance(account.getBalance() - dto.getBalance());
        ResponseAccount newAccount = accountMapper.map(accountRepository.save(account));
        log.info("IN updateAccount - account: {} updated", account);
        return newAccount;
    }

    @Override
    public ResponseWrapperAccounts getAllAccounts(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Long totalAmount = accountRepository.count();

        List<ResponseAccount> dtoList = new ArrayList<>(accountRepository
                .findAll(pageable).stream()
                .map(accountMapper::map)
                .toList());

        return new ResponseWrapperAccounts(totalAmount, dtoList);
    }

    @Override
    public ResponseAccount transfer(TransferAccount dto, String password) {

        Account account = checkAccount(dto.getFromAccountId());

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new AuthenticationException(password);
        }

        if (account.getBalance() - dto.getBalance() < 0) {
            throw new RemoveFromBalanceException(account.getBalance());
        }

        ResponseAccount fromBalance = removeFromBalance(
                new UpdateBalance(dto.getFromAccountId(), dto.getBalance()),
                password);
        replenishBalance(new UpdateBalance(dto.getToAccountId(), dto.getBalance()));

        log.info("IN transferAccount - account: {} transferred", fromBalance);
        return fromBalance;
    }

    private Account checkAccount(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundAccountException(id));
    }
}

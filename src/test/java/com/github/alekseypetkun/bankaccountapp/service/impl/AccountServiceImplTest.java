package com.github.alekseypetkun.bankaccountapp.service.impl;

import com.github.alekseypetkun.bankaccountapp.dto.*;
import com.github.alekseypetkun.bankaccountapp.entity.Account;
import com.github.alekseypetkun.bankaccountapp.exception.AuthenticationException;
import com.github.alekseypetkun.bankaccountapp.mapper.AccountMapper;
import com.github.alekseypetkun.bankaccountapp.repository.AccountRepository;
import com.github.alekseypetkun.bankaccountapp.security.PBFDK2PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private PBFDK2PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testAddAccount() {

        // Создаем тестовые данные
        CreateAccount createDto = new CreateAccount("счет 1", "1111");

        // Определяем ожидаемый результат
        ResponseAccount expectedDto = new ResponseAccount(1L, "счет 1", 0);

        // Определяем результат, который вернет метод accountService.addAccount()
        Account entity = new Account();
        when(accountRepository.findAccountByUsername("счет 1")).thenReturn(null);
        when(accountMapper.map(createDto)).thenReturn(entity);
        when(accountRepository.save(entity)).thenReturn(entity);
        when(accountMapper.map(entity)).thenReturn(expectedDto);

        // Вызываем метод accountService.addAccount() и проверяем результат
        ResponseAccount actualDto = accountService.addAccount(createDto);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    public void testReplenishBalance() {

        // Создаем тестовые данные
        UpdateBalance updateBalance = new UpdateBalance();
        updateBalance.setAccountId(1L);
        updateBalance.setBalance(100);

        // Определяем результат, который вернет метод accountService.replenishBalance()
        Account account = new Account();
        account.setId(1L);
        account.setUsername("счет 1");
        account.setPassword("encodedPassword");
        account.setBalance(50);
        ResponseAccount responseAccount = new ResponseAccount();
        responseAccount.setId(1L);
        responseAccount.setUsername("счет 1");
        responseAccount.setBalance(150);
        when(accountRepository.findById(updateBalance.getAccountId())).thenReturn(Optional.of(account));
        when(accountMapper.map(account)).thenReturn(responseAccount);
        when(accountRepository.save(account)).thenReturn(account);

        // Вызываем метод accountService.replenishBalance() и проверяем результат
        ResponseAccount result = accountService.replenishBalance(updateBalance);

        assertEquals(responseAccount, result);
    }

    @Test
    public void testRemoveFromBalance() {

        // Создаем тестовые данные
        UpdateBalance updateBalance = new UpdateBalance();
        updateBalance.setAccountId(1L);
        updateBalance.setBalance(50);

        // Определяем результат, который вернет метод accountService.removeFromBalance()
        Account account = new Account();
        account.setId(1L);
        account.setUsername("счет 1");
        account.setPassword("encodedPassword");
        account.setBalance(100);

        ResponseAccount responseAccount = new ResponseAccount();
        responseAccount.setId(1L);
        responseAccount.setUsername("счет 1");
        responseAccount.setBalance(50);

//        when(passwordEncoder.encode(account.getPassword())).thenReturn("encodedPassword");
        when(passwordEncoder.matches("encodedPassword", account.getPassword())).thenReturn(true);
        when(accountRepository.findById(updateBalance.getAccountId())).thenReturn(Optional.of(account));
        when(accountMapper.map(account)).thenReturn(responseAccount);
        when(accountRepository.save(account)).thenReturn(account);

        // Вызываем метод accountService.removeFromBalance() и проверяем результат
        ResponseAccount result = accountService.removeFromBalance(updateBalance, "encodedPassword");

        assertEquals(responseAccount, result);
    }

    @Test
    public void testGetAllAccounts() {

        // Создаем тестовые данные
        int pageNumber = 0;
        int pageSize = 10;
        Long totalAmount = 100L;
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account());
        accountList.add(new Account());
        accountList.add(new Account());
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Определяем результат, который вернет метод accountService.getAllAccounts()
        when(accountRepository.count()).thenReturn(totalAmount);
        when(accountRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(accountList));
        when(accountMapper.map(any(Account.class))).thenReturn(new ResponseAccount(), new ResponseAccount(), new ResponseAccount());

        // Вызываем метод accountService.getAllAccounts() и проверяем результат
        ResponseWrapperAccounts response = accountService.getAllAccounts(pageNumber, pageSize);

        assertEquals(totalAmount, response.getCount());
        assertEquals(3, response.getResults().size());
    }

    @Test
    public void testTransfer_AuthenticationException() {

        Long fromAccountId = 1L;
        Integer balance = 100;
        String password = "password";
        Account fromAccount = new Account();
        fromAccount.setId(fromAccountId);
        fromAccount.setBalance(1000);
        fromAccount.setPassword("encodedPassword");
        when(accountRepository.findById(fromAccountId)).thenReturn(Optional.of(fromAccount));
        when(passwordEncoder.matches(password, fromAccount.getPassword())).thenReturn(false);

        assertThrows(AuthenticationException.class, () -> {
            accountService.transfer(new TransferAccount(fromAccountId, 2L, balance), password);
        });
    }
}
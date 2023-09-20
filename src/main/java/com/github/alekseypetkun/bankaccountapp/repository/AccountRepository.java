package com.github.alekseypetkun.bankaccountapp.repository;

import com.github.alekseypetkun.bankaccountapp.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Поиск аккаунта по названию
     *
     * @param username название аккаунта
     * @return найденный аккаунт
     */
    Account findAccountByUsername(String username);

    /**
     * Показать все сохраненные аккаунты
     *
     * @param pageable пагинация
     * @return страница аккаунтов
     */
    @Override
    Page<Account> findAll(Pageable pageable);
}

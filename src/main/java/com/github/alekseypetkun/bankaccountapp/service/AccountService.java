package com.github.alekseypetkun.bankaccountapp.service;

import com.github.alekseypetkun.bankaccountapp.dto.*;

/**
 * Сервис по работе с аккаунтом
 */
public interface AccountService {

    /**
     * Создание нового аккаунта
     *
     * @param dto параметры аккаунта
     * @return новый аккаунт
     */
    ResponseAccount addAccount(CreateAccount dto);

    /**
     * Пополнение баланса аккаунта по его идентификатору
     *
     * @param dto параметры
     * @return измененный аккаунт
     */
    ResponseAccount replenishBalance(UpdateBalance dto);

    /**
     * Снятие средств с баланса по его идентификатору
     *
     * @param dto      параметры
     * @param password пин-код
     * @return измененный аккаунт
     */
    ResponseAccount removeFromBalance(UpdateBalance dto, String password);

    /**
     * Получить все аккаунты пользователя
     *
     * @param pageNumber параметр начальной страницы
     * @param pageSize   параметр конечной страницы
     * @return список аккаунтов
     */
    ResponseWrapperAccounts getAllAccounts(Integer pageNumber, Integer pageSize);

    /**
     * Перевод средств из одного аккаунта в другой
     *
     * @param dto      параметры
     * @param password пин-код
     * @return измененный аккаунт
     */
    ResponseAccount transfer(TransferAccount dto, String password);
}

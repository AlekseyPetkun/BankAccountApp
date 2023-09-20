package com.github.alekseypetkun.bankaccountapp.mapper;

import com.github.alekseypetkun.bankaccountapp.dto.CreateAccount;
import com.github.alekseypetkun.bankaccountapp.dto.ResponseAccount;
import com.github.alekseypetkun.bankaccountapp.entity.Account;
import org.mapstruct.Mapper;

/**
 * Маппинг сущности аккаунта
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    /**
     * Преобразует сущность в дто
     *
     * @param entity сущность
     * @return дто
     */
    ResponseAccount map(Account entity);

    /**
     * Преобразует дто в сущность
     *
     * @param dto дто
     * @return сущность
     */
    Account map(CreateAccount dto);
}

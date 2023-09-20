package com.github.alekseypetkun.bankaccountapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO информация о счете
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseAccount {

    private Long id; // идентификатор аккаунта
    private String username; // логин пользователя
    private Integer balance; // баланс пользователя
}

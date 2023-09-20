package com.github.alekseypetkun.bankaccountapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO информация об аккаунте
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountDto {

    private Long id; // идентификатор аккаунта
    private String username; // логин пользователя

    // поле будет записано только как часть десериализации,
    // но значение поля не включается в сериализацию
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password; // пин-код пользователя
    private Integer balance; // баланс пользователя
}

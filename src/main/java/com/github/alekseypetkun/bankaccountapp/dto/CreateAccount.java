package com.github.alekseypetkun.bankaccountapp.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО создание аккаунта
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateAccount {

    @NotEmpty(message = "строка с логином не может быть пустой!")
    @Size(min = 2, max = 64)
    private String username; // логин пользователя

    @NotEmpty(message = "строка с пин-кодом не может быть пустой!")
    @Pattern(regexp = "\\d{4}", message = "пин-код состоит из 4-х цифр")
    private String password; // пин-код пользователя
}

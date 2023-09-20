package com.github.alekseypetkun.bankaccountapp.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO изменение баланса
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateBalance {

    @NotNull(message = "строка с id не может быть пустой!")
    @Positive
    private Long accountId; // идентификатор изменяемого аккаунта

    @NotNull(message = "строка с балансом не может быть пустой!")
    @Positive
    private Integer balance; // изменяемый баланс пользователя
}

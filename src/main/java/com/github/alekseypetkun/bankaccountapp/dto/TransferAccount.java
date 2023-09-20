package com.github.alekseypetkun.bankaccountapp.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * DTO изменение баланса
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransferAccount {

    @NotNull(message = "строка с id не может быть пустой!")
    @Positive
    private Long fromAccountId; // идентификатор аккаунта, из которого переводят деньги

    @NotNull(message = "строка с id не может быть пустой!")
    @Positive
    private Long toAccountId; // идентификатор аккаунта, в который переводят деньги

    @NotNull(message = "строка с балансом не может быть пустой!")
    @Positive
    private Integer balance; // изменяемый баланс пользователя
}

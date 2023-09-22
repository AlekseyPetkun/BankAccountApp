package com.github.alekseypetkun.bankaccountapp.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class UpdateBalance {

    @Schema(description = "идентификатор изменяемого аккаунта")
    @NotNull(message = "строка с id не может быть пустой!")
    @Positive
    private Long accountId;

    @Schema(description = "изменяемый баланс пользователя")
    @NotNull(message = "строка с балансом не может быть пустой!")
    @Positive
    private Integer balance;
}

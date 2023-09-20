package com.github.alekseypetkun.bankaccountapp.controller;

import com.github.alekseypetkun.bankaccountapp.dto.*;
import com.github.alekseypetkun.bankaccountapp.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер по работе с аккаунтами.
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@Tag(name = "API по работе с аккаунтами")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @Operation(
            summary = "Добавление нового аккаунта",
            description = "Нужно заполнить параметры для создания аккаунта",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Аккаунт был добавлен (Created)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseAccount.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Аккаунт не добавлен, " +
                                    "т.к. не прошел валидацию (Bad Request)"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Аккаунт не добавлен, " +
                                    "т.к. такое имя уже существует (Conflict)"
                    )
            }
    )
    public ResponseAccount addAccount(@RequestBody @Valid CreateAccount dto) {

        return accountService.addAccount(dto);
    }

    @PostMapping("/replenish")
    @Operation(
            summary = "Пополнение баланса",
            description = "Нужно заполнить параметры для пополнения баланса",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Баланс был пополнен (OK)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseAccount.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Баланс не пополнен, " +
                                    "т.к. не прошел валидацию (Bad Request)"
                    )
            }
    )
    public ResponseAccount replenishBalance(@RequestBody @Valid UpdateBalance dto) {

        return accountService.replenishBalance(dto);
    }

    @PostMapping("/remove")
    @Operation(
            summary = "Снятие средств с баланса",
            description = "Нужно заполнить параметры для снятия баланса и ввести пин-код",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Средства были сняты с баланса (OK)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseAccount.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Средства не были сняты с баланса, " +
                                    "т.к. не прошли валидацию (Bad Request)"
                    )
            }
    )
    public ResponseAccount removeFromBalance(@RequestBody @Valid UpdateBalance dto,
                                             @RequestParam String password) {

        return accountService.removeFromBalance(dto, password);
    }

    @GetMapping
    @Operation(
            summary = "Получить все аккаунты пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список аккаунтов (Ok)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperAccounts.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка, не зависящая от вызывающей стороны"
                    )
            }
    )
    public ResponseWrapperAccounts getAllAccounts(
            @RequestParam(required = false, value = "page_number", defaultValue = "0") @Min(0) Integer pageNumber,
            @RequestParam(required = false, value = "page_size", defaultValue = "10") Integer pageSize) {

        return accountService.getAllAccounts(pageNumber, pageSize);
    }

    @PostMapping("/transfer")
    @Operation(
            summary = "Перевод средств на другой счет",
            description = "Нужно заполнить параметры для перевода баланса и ввести пин-код",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Средства были переведены (OK)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseAccount.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Средства не были переведены, " +
                                    "т.к. не прошли валидацию (Bad Request)"
                    )
            }
    )
    public ResponseAccount transfer(@RequestBody @Valid TransferAccount dto,
                                    @RequestParam String password) {

        return accountService.transfer(dto, password);
    }
}

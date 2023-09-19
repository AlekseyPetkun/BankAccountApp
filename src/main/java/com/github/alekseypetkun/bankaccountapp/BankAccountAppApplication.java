package com.github.alekseypetkun.bankaccountapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "BankAccountApplication",
        description = "Веб-приложение по типу банковского приложения для создания счетов"
))
public class BankAccountAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountAppApplication.class, args);
    }

}

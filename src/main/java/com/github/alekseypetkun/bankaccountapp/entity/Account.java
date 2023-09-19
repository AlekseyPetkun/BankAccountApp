package com.github.alekseypetkun.bankaccountapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность аккаунт
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

    /**
     * Идентификатор аккаунта
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Логин пользователя
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * Пин-код пользователя
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Баланс пользователя
     */
    @Column(name = "balance", nullable = false)
    private Integer balance;
}

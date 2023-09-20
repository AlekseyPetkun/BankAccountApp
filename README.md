## BankAccountApp

*RESTful API для банковского приложения, позволяющая пользователям создавать счета, пополнять или снимать средства, переводить средства с одного счета на другой и выводить все имеющиеся счета у пользователя*

## Бэкенд-часть проекта предполагает реализацию следующего функционала: ##

1. Создание нового счета (аккаунта):
   
Пользователи могут зарегистрироваться, указав имя и 4-х значный пин-код:

`CreateAccount`
```json
{
  "username": "счет 1",
  "password": "2571"
}
```
В ответ получают информацию о новом счете:

`ResponseAccount`
```json
{
  "id": 1,
  "username": "счет 1",
  "balance": 0
}
```

2. Пополнение баланса:

Пользователи могут пополнить баланс, указав идентификатор пополняемого счета (аккаунта):

`UpdateBalance`
```json
{
  "account_id": 1,
  "balance": 5000
}
```
В ответ получают информацию об измененном счете:

`ResponseAccount`
```json
{
  "id": 1,
  "username": "счет 1",
  "balance": 5000
}
```

3. Снятие средств со счета (аккаунта):

Пользователи могут снять средства со счета, указав идентификатор счета (аккаунта) и указать правильный пин-код:

`UpdateBalance`
```json
{
  "account_id": 1,
  "balance": 500
}
```
В ответ получают информацию об измененном счете:

`ResponseAccount`
```json
{
  "id": 1,
  "username": "счет 1",
  "balance": 4500
}
```

4. Перевод средств с одного счета на другой:

Пользователи могут переводить средства со счета на счет, указав идентификатор счета (аккаунта), с которого будут переводиться средства и идентификатор пополняемого счета, а также указать правильный пин-код:

`TransferAccount`
```json
{
  "from_account_id": 1,
  "to_account_id": 2,
  "balance": 1000
}
```
В ответ получают информацию об измененном счете:

`ResponseAccount`
```json
{
  "id": 1,
  "username": "счет 1",
  "balance": 3500
}
```

5. Получение всех счетов (аккаунтов) пользователя:

Пользователи могут получить список всех своих счетов:

`ResponseWrapperAccounts`
```json
{
"count": 2,
    "results": [
    {
        "id": 1,
        "username": "счет 1",
        "balance": 3500
    },
    {
        "id": 2,
        "username": "счет 2",
        "balance": 1000
    }
    ]
}
```

---

## Спецификация OpenAPI для описания API ##

[OpenAPI specification](openapi.yaml "OpenAPI")

## Использован следующий стек технологий: ##

Java17\
SpringBoot 3\
Security\
Hibernate\
PostgreSQL\
Lombok\
Gradle\
Flyway\
Mapstruct\
OpenAPI\
JUnit\
Mockito

## Структура базы данных: ##

![](https://github.com/AlekseyPetkun/BankAccountApp/blob/master/screens/Схема%20БД.png)

##

[![Typing SVG](https://readme-typing-svg.herokuapp.com?color=%2336BCF7&lines=thank+you+for+your+attention)](https://git.io/typing-svg)

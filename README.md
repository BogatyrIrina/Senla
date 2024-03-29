# Образовательный Java-проект

## Описание

Этот проект является учебным примером Java-приложения.
Он представляет собой простое RESTful приложение, реализованное на Java с использованием Spring,
демонстрирующее CRUD операции для сущности `User`.
В проекте реализованы ролевая модель с различными уровнями доступа (Admin, User), регистрация и логин пользователя, а также применены механизмы шифрования паролей и авторизации через JWT-токены.
Приложение основано на фитнес системе позволяющей получить пользователя и его тренеров и тренировки, а так же адреса пользователя. Можно записаться на тренировку, посмотреть расписание, посмотреть свой график тренировок.

## Технологический стек

- **Spring Framework**: WebMVC, Context, Security, ORM
- **Liquibase**: для управления версиями базы данных
- **PostgreSQL**: основная система управления базой данных
- **Hibernate**: ORM-библиотека для работы с базой данных
- **H2Database**: в памяти база данных для тестирования
- **JUnit**: фреймворк для модульного тестирования
- **Mockito**: для мокирования зависимостей при тестировании
- **Spring Security Test**: для тестирования компонентов безопасности

## Функциональные требования

- Использование **Docker Compose** для оркестрации контейнеров
- Развертывание приложения на сервере приложений **Tomcat**
- Реализация ролевой модели с уровнями доступа Admin и User
- Предоставление функционала регистрации и логина для пользователя
- Использование **JWT-токенов** для авторизации
- Шифрование паролей перед сохранением в базу данных
- Реализация отношений базы данных Один ко Многим, Многие ко Многим и Один к Одному

## Схема таблиц
![img.png](src/main/resources/erd/db_schema.png)

## Примеры вызовов

Приложение позволяет зарегистрироваться (Пример для получения токена нового пользователя):

    curl -X POST --location "http://localhost:8080/my-study/api/security/register" \
    -H "Content-Type: application/json" \
    -d "{
    \"userName\": \"Irina\",
    \"userEmail\": \"test@mail.ru\",
    \"password\": \"123_5\"
    }"

После чего можно залогиниться и получить JWT-токен
(пример для получения токена Админа):

    curl -X POST --location "http://localhost:8080/my-study/api/security/login" \
    -H "Content-Type: application/json" \
    -d "{
    \"username\": \"1\",
    \"password\": \"123_1\"
    }"

Дальше вызывать остальные сервисы приложения передав в header
Authorization : Bearer Ваш_токен

Записаться на тренировку

    curl -X POST --location "http://localhost:8080/my-study/api/schedule/register-to-training?training_id=4" \
    -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiZXhwIjoxNzA1ODM4NzgwLCJpYXQiOjE3MDU4Mzg0ODB9.0_-DQ4XPrhUAONzP5G59pm6T4DEWXP8FtIb4iHj3i-F23eZVcIsCRhEkMTDeZ-acOdphBLGNiTHLi9XK3wvzOQ"

Посмотреть все доступные тренировки

    curl -X GET --location "http://localhost:8080/my-study/api/business/get-schedule" \
    -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNzA1Njg2MTk0LCJpYXQiOjE3MDU2ODU4OTR9.kj7lMfpH9ovdR01deayATWMXr2cE4ZpfhfgHDz2qaMIC2Hty9sHbKnPMUPHZxAu9fhkv2R5xN32Occ-PS2Jp2A"

Получить персональные тренировки

    curl -X GET --location "http://localhost:8080/my-study/api/schedule/get-personal-schedule" \
    -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiZXhwIjoxNzA2MDI3MDY2LCJpYXQiOjE3MDYwMjY3NjZ9.BLqFcY3y0zThYl5EY0SOPlWy63tB97QohNCzwk4lPQ4C0hSuBazpk0wsmE1f_55SPJ9rG1Z1NT0bh5hSuGTFGg"

## Разработчик
Богатырь Ирина Евгеньевна


Файл можно не редактировать, ибо все буду переписывать.
Проект, защита, презентация, записка будут на английском.
Возможно, это будет 10 минут позора)), т.к. я не настолько хорошо на нем говорю, but I'll try)


# Дипломная работа
Тема: сайт для приюта домашних животных

## Основной функционал
Сделано:

1. Добавление информации о животных в приюте (разделы котов и собак)
2. Редактирование информации о животных
3. Просмотр подробной информации о животном
4. Раздел для добавления постов об уходе за питомцами
5. Получение информации со стороннего сайта и отображение на моем (rest) в разделе постов. Weather from https://openweathermap.org, rates from private24
6. Редактирование, просмотр постов
7. Секьюрити. Не аутентифицированному пользователю нет возможности просматривать post
8. Админ может добавлять животных и посты
9. Аутентификация через google

В процессе:

1. Tests
2. Init sql

## Technologies used

* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Mysql
* REST
* Ajax
* Thymeleaf
* Bootstrap
* HTML, CSS, js
* JUnit
* Mockito
* Rest Assured

## How to run

* Go to the app  folder in project 
> cd pet/app
* Run springboot application 
> mvn spring-boot:run
* And open in browser: 
> http://localhost:8080

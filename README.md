<img src="readme_resourses/logos/stepik_logo.png" width="284" height="76" alt="Логотип Stepik">

Это небольшой проект по автоматизации сайта онлайн-курсов "stepik"
https://stepik.org

# Содержание

1. [Описание функционала](#описание-функционала)
2. [Тестирование](#тестирование)
  - [UI тесты](#ui)
  - [API тесты](#api)
3. [Технологии](#технологии)
4. [Запуск](#запуск)
  - [Запуск из Jenkins](#запуск-из-jenkins)
  - [Запуск из Allure TestOps](#запуск-из-allure-testops)
  - [Запуск тестов из консоли](#запуск-тестов-из-консоли)
5. [Отчетность о запусках тестов](#отчетность-о-запусках-тестов)
  - [Allure отчет](#allure-отчет)
  - [Видео запуска теста](#видео-запуска-теста)
  - [TestOps отчет](#testops-отчет)
  - [Отчет о запуске в Telegram](#отчет-о-запуске-в-telegram)
6. [Тест-кейсы](#тест-кейсы)

## Описание функционала

Проект представляет собой комплексную систему автоматизированного тестирования, включающую как UI, так и API тесты для проверки функциональности образовательной платформы Stepik. 

Проект реализован с использованием современных подходов к автоматизации, включая:
- Параметризированные тесты для проверки различных сценариев
- Интеграцию с системами мониторинга и отчетности
- Автоматическое создание отчетов в различных форматах
- Интеграцию с CI/CD пайплайнами
- Автоматическую отправку уведомлений о результатах тестирования в Telegram

## Тестирование

В данном проекте реализованы тесты:
- [x] UI :
  - searchByParamsTest - параметризированная проверка поиска по одному параметру
  - searchTwoParametersTest - параметризированная проверка поиска по двум параметрам
  - stepikCheckLocaleTest - параметризированная проверка локализации кнопок главного меню
  - validateAuthorisationFormTest - проверка компонентов страницы авторизации
  - cancelAuthorisationTest - проверка отмены авторизации

- [x] API:
  - getCourse - получение информации о курсе
  - getCourseNotFound - негативная проверка получения информации о курсе
  - getUserDetails - получение информации о пользователе
  - getUserNotFound - негативная проверка получения информации о пользователе
  - loginWithInvalidCredentials - негативная проверка авторизации

## Технологии

[![Java](/readme_resourses/logos/java_logo.png)](https://www.java.com)
[![Gradle](/readme_resourses/logos/gradle_logo.png)](https://gradle.org)
[![Selenide](/readme_resourses/logos/selenide_logo.png)](https://selenide.org)
[![Selenoid](/readme_resourses/logos/jenkins.png)](https://aerokube.com/selenoid)
[![Junit](/readme_resourses/logos/junit_logo.png)](https://junit.org/junit5)
[![Allure](/readme_resourses/logos/allure_logo.png)](https://docs.qameta.io/allure)
[![Telegram](/readme_resourses/logos/tg_logo.png)](https://telegram.org)
[![Intelij_IDEA](/readme_resourses/logos/intelij_idea.png)](https://www.jetbrains.com/idea)
[![Jenkins](/readme_resourses/logos/jenkins.png)](https://www.jenkins.io)


## Запуск

1. Запуск из Jenkins
https://jenkins.autotests.cloud/job/QA_java32_olganow_hw23/configure

<img src="readme_resourses/screenshots/jenkins_test.png" width="750" height="355">

2. Запуск из allure testop:
https://allure.autotests.cloud/project/4712/jobs

<img src="readme_resourses/screenshots/testops_jobs.png" width="750" height="248">

3. Запуск тестов из консоли:
```
 ./gradlew clean api   
```

## Отчетность о запусках тестов

- [x] Allure отчет
<img src="readme_resourses/screenshots/allure_report.png" width="750" height="484">

https://allure.autotests.cloud/project/4712/dashboards

- [x] Видео запуска теста
<img src="readme_resourses/screenshots/test_video.gif" width="750" height="421">

https://jenkins.autotests.cloud/job/QA_java32_olganow_hw23/17/allure/#suites/907e863b9441e9e25c62507bfa8ae132/c392a97948804df8/

- [x] TestOps отчет
  <img src="readme_resourses/screenshots/testops_test.png" width="750" height="477">
https://allure.autotests.cloud/launch/45814

- [x] Отчет о запуске в Telegram
  - Пример отчета:
  <img src="readme_resourses/screenshots/tg_report.png" width="428" height="433">

## Тест-кейсы
1) Заголовок "Пользователь не сможет авторизоваться на сайте, если введет неверный пароль"
Шаги
* Перейти на сайт stepik.org
* Нажать на кнопку "Войти"
* Проверить заголовок формы входа на сайт
* Ввести email
* Ввести некорректный пароль
* Нажать кнопку "Войти"
Ожидаемый результат: Появилось сообщение "E-mail адрес и/или пароль не верны."

2) Заголовок "Форма авторизации закроется, если нажать на Х "
Шаги
* Перейти на сайт stepik.org
* Нажать на кнопку "Войти"
* Проверить заголовок формы входа на сайт
* Нажать кнопку "Х
Ожидаемый результат: Форма авторизации закрылась









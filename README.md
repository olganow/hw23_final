<img src="readme_resourses/logos/stepik_logo.png" width="284" height="76" alt="Логотип Stepik">

## Описание функционала

Это небольшой проект по автоматизации сайта онлайн-курсов "stepik"
https://stepik.org

### Тестирование

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

### Технологии

![This is an image](/readme_resourses/logos/java_logo.png)![This is an image](/readme_resourses/logos/gradle_logo.png)
![This is an image](/readme_resourses/logos/selenide_logo.png)![This is an image](/readme_resourses/logos/junit_logo.png)
![This is an image](/readme_resourses/logos/allure_logo.png)![This is an image](/readme_resourses/logos/tg_logo.png)

### Запуск

1. Запуск из Jenkins
https://jenkins.autotests.cloud/job/QA_java32_olganow_hw23/configure

2. Запуск тестов из консоли:
```
 ./gradlew clean api   
```

### Отчетность о запусках тестов

- [x] Allure отчет
  - Пример отчета:
<img src="readme_resourses/screenshots/allure_report.png" width="750" height="484">

- [x] Отчет о запуске в Telegram
  - Пример отчета:
  <img src="readme_resourses/screenshots/tg_report.png" width="428" height="433">









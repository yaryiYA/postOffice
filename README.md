# Приложение postOffice
## Описание проекта

Почтовое отслеживание API предоставляет возможность регистрировать почтовые отправления, отслеживать их передвижение между почтовыми отделениями и получать информацию о статусе и истории движения конкретного почтового отправления.

## Технологии
- Java SE и Spring Framework для реализации API.
- RESTful архитектура для общения с API.
- JSON  для обмена данных с API.
- СУБД PostgreSQL.
- Gradle для сборки проекта.
- Apache Tomcat-сервер приложений для запуска API.

## Сущности
Parcel(почтовое отправление)
- id - уникальный идентификатор почтового отправления.
- parcelType - тип почтового отправления (письмо, посылка, бандероль, открытка).
- recipientIndex - почтовый индекс получателя.
- recipientAddress - адрес получателя.
- имя получателя - имя получателя.

Department(Почтовое отделение)
- index - почтовый индекс отделения.
- name - название отделения.
- departmentAddress - адрес отделения.

HistoryPoint(поинт передвижения)
- pointType - тип поинта(регистрация, прибытие в отделение, убытие из отделения, получено адресатом).
- appointmentDate - временной метке события.
- indexDepartment - индекс текущего отделения.

## Сервис

Сервис почтового отслеживания предоставляет несколько операций для работы с почтовыми отправлениями. Давайте подробнее рассмотрим каждую операцию:

1. RegistrationParcel - метод регистрации почтового отправления принимает объект Parcel и индекс начального отделения. Parcel содержит информацию о типе почтового отправления, индексе получателя, адресе получателя и имени получателя. Метод создает новое почтовое отправление, присваивает ему уникальный идентификатор и регистрирует его в системе. Затем почтовое отправление помещается в начальное отделение.

2. ArriveAtDepartment - метод отслеживания прибытия почтового отправления в промежуточное отделение принимает идентификатор почтового отправления и индекс промежуточного отделения. Метод создает запись о прибытии почтового отправления в заданное отделение и обновляет статус почтового отправления в системе.

3. LeaveDepartment - метод отслеживания убытия почтового отправления из промежуточного отделения принимает идентификатор почтового отправления. Метод создает запись об убытии почтового отправления из текущего отделения и обновляет статус почтового отправления в системе.

4. DeliveryToRecipient - метод для отслеживания доставки почтового отправления получателю принимает идентификатор почтового отправления. Метод создает запись о доставке почтового отправления получателю и обновляет статус почтового отправления в системе.

5. GetHistoryParcel: Метод для получения полной истории движения почтового отправления по его идентификатору. Метод возвращает список объектов HistoryPoint, каждый из которых содержит информацию об определенном событии (регистрация, прибытие в отделение, убытие из отделения, получено адресатом) и временной метке события.

Каждая операция использует идентификатор почтового отправления для идентификации и связывания событий и изменения статуса. Методы могут возвращать объект Parcel с обновленными данными или список объектов HistoryPoint в зависимости от операции.

Данные операции реализованы внутри класса ParcelSerivce, который будет обрабатывать логику сервиса по отслеживанию почтовых отправлений. Класс ParcelRepository может взаимодействовать с базой данных для хранения информации о почтовых отправлениях, отделениях и их перемещениях.

## Инструкция по развертыванию и запуску проекта

1. Установите Java и сервер приложений (например, Apache Tomcat).
2. Установите и настройте выбранную СУБД.
3. Склонируйте проект с репозитория: git clone https://github.com/yaryiYA/postOffice
5. Откройте файл src/main/resources/application.properties и настройте подключение к СУБД.
4. Скомпилируйте и упакуйте проект с помощью Gradle.
6. Скопируйте полученный war-файл в директорию сервера приложений.
7. Запустите сервер приложений.
8. Описание API в SoapUI.
9. Индекс покрытия тестами в папке index.
Вы можете найти описание запросов и ответов API в папке soapUI, файле PostTrackingAPI-soapui-project.xml.


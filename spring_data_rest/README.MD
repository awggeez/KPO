1) В application.properties нужно указать данные своей БД
2) Скрипт для создания БД: 
 
CREATE TABLE employees ( id SERIAL PRIMARY KEY, name varchar(15), surname varchar(25), department varchar(20), salary int );
INSERT INTO employees (name, surname, department, salary) VALUES ('Luis', 'Adam', 'IT', 1000), ('Taylor', 'Smith', 'IT', 700), ('Anton', 'Smirnov', 'HR', 850);

*Вместо VALUES вставляются любые подходящие данные

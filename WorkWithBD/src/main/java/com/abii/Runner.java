package com.abii;

import com.abii.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Runner {
    private static long id = 1;

    public static void main(String[] args) throws SQLException {
        try (var connection = ConnectionManager.open()) {
            // для удаления таблицы
//            dropTable(connection);

            // создание таблицы
            createTable(connection);

            // инициализация пользователей, которые будут храниться в БД
            User user1 = new User(id++, "John", "123");
            User user2 = new User(id++, "Mary", "456");
            User user3 = new User(id++, "Bob", "789");
            User user4 = new User(id++, "Alice", "101112");
            User user5 = new User(id++, "Kate", "131415");

            // сохранить пользователей в БД
            registerUsers(user1, user2, user3, user4, user5);

            // проверяем корректность авторизации по имени и паролю
            while (true) {
                System.out.println("Введите имя и пароль для авторизации (для выхода введите exit):");
                String name = new Scanner(System.in).nextLine();
                if ("exit".equals(name)) {
                    break;
                }
                String password = new Scanner(System.in).nextLine();
                authenticateUsers(name, password);
            }
        }
    }

    private static void authenticateUsers(String name, String password) throws SQLException {
        String sql = """
                SELECT * FROM bd
                WHERE name = ? AND password = ?
                """;
        try (var connection = ConnectionManager.open()) {
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.execute();

            if (preparedStatement.getResultSet().next()) {
                System.out.println("Пользователь с таким именем и паролем существует");
            } else {
                System.out.println("Пользователь с таким именем и паролем не существует");
            }
        }
    }

    private static void registerUsers(User user1, User user2, User user3, User user4, User user5) {
        String sql = """
                INSERT INTO bd (name, password)
                VALUES (?, ?)
                """;
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user1.getName());
            preparedStatement.setString(2, user1.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, user2.getName());
            preparedStatement.setString(2, user2.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, user3.getName());
            preparedStatement.setString(2, user3.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, user4.getName());
            preparedStatement.setString(2, user4.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, user5.getName());
            preparedStatement.setString(2, user5.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dropTable(Connection connection) {
        String sql = """
                DROP TABLE IF EXISTS bd
                """;
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS bd (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL UNIQUE,
                password VARCHAR(255) NOT NULL
                )
                """;
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}
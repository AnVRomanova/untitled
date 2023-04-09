package jm.task.core.jdbc;
import jm.task.core.jdbc.bl.Util;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Коля", "Иванов", (byte) 23);
        userService.saveUser("Иван", "Шишкин", (byte) 56);
        userService.saveUser("Александр", "Пушкин", (byte) 32);


        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}

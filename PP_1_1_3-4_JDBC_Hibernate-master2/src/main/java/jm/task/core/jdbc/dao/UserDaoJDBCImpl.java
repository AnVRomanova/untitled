package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.bl.Util;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();
    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {

    }

    public void createUsersTable() throws SQLException {
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS users2 (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT)";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Database has been created!");
        } catch(SQLException e) {
            System.out.println("Connection failed...");
            throw e;

        } finally {
            if(statement != null){
                //statement.close();
            }
            if(connection != null) {
                //connection.close();
            }
        }

    }





    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users2");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement ps = null;
        String sql = "INSERT INTO users2 (name, lastName, age) VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();

        } catch(SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                //ps.close();
            }
            if (connection != null) {
                //connection.close();
            }

        }

    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement ps = null;
        String sql = "DELETE FROM users2 where id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            if(ps != null) {
                //ps.close();
            }
            if (connection != null){
                //connection.close();
            }

        }

    }

    public List<User> getAllUsers() throws SQLException {

        List<User> usersList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age from users2 ";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                User user =  new User();
                user.setId(resultSet.getLong("id"));

                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            if(statement != null) {
                //statement.close();
            }
            if(connection != null) {
                //connection.close();
            }
         }

        return usersList;
    }

    public void cleanUsersTable() throws SQLException {


        String sql = "TRUNCATE TABLE mydbbase.users2";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            throw e;
        } finally {
            if(statement != null) {
                //statement.close();
            }
            if(connection != null) {
                //connection.close();
            }

    }
    }

}

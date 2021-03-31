package org.fiipractic.repository;

import org.fiipractic.config.DbConnection;
import org.fiipractic.exception.NotFoundException;
import org.fiipractic.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    Connection con = DbConnection.getConnection();

    public void create(User userFromRq) {
        try {
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into user (userName,firstName,lastName,email,password) values ('" + userFromRq.getUserName() + "','" + userFromRq.getFirstName() + "','" + userFromRq.getLastName() + "','" + userFromRq.getEmail() + "','" + userFromRq.getPass() + "')");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAll() {
        User user;
        List<User> users = new ArrayList<>();
        try {
            Statement mystmt = con.createStatement();
            String sql = "select *from user";
            ResultSet rs = mystmt.executeQuery(sql);
            while (rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException ex) {
            // handle any errors
            user = new User();
            user.setFirstName(ex.getMessage());
            System.out.println("SQLException: {}" + ex.getMessage());
        }

        return users;
    }

    public User findByUserName(String userName) {
        User user;
        try {
            Statement mystmt = con.createStatement();
            String sql = "select *from user WHERE userName='" + userName + "'";
            ResultSet rs = mystmt.executeQuery(sql);
            if (rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
                return user;
            }
        } catch (SQLException ex) {

            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return null;
    }

    public User findById(Long id) {
        User user;
        try {
            Statement mystmt = con.createStatement();
            String sql = "select *from user WHERE id='" + id + "'";
            ResultSet rs = mystmt.executeQuery(sql);
            if (rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
                return user;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return null;
    }
}

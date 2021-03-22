package org.fiipractic.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.jstl.Logger;
import org.fiipractic.model.User;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.fiipractic.exception.NotFoundException;

@Component
public class UserService {
    ///private static final Logger LOGGER = Logger.get(UserService.class);

    private final List<User> users = new ArrayList<>();


    public void create(User userFromRq) {
        //logger.info("bullshit");
        User user = new User();

            String jdbcURL = "jdbc:mysql://localhost:3306/twitter";
            String usernameConnection = "adrian.lupu";
            String passwordConnection = "password";
        try {
            //JdbcTemplate jdbcTemplateObject=new JdbcTemplate();
            //DataSource dataSource;
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, usernameConnection, passwordConnection);
            Statement mystmt= connection.createStatement();
            mystmt.executeUpdate("insert into user (userName,firstName,lastName,email,password) values ('"+userFromRq.getUserName()+"','"+userFromRq.getFirstName()+"','"+userFromRq.getLastName()+"','"+userFromRq.getEmail()+"','"+userFromRq.getPass()+"')");
            //String sql="insert into user (userName,firstName) values(?,?)";
            //jdbcTemplateObject.update(sql,userFromRq.getUserName(),userFromRq.getFirstName());
            //return;
        } catch (SQLException |ClassNotFoundException ex) {
            // handle any errors
            //user.setUserName(ex.getMessage().toString());
            System.out.println("SQLException: {}" + ex.getMessage());
        }


        //user.setId((long) users.size() + 1);

        //user.setFirstName("no");
       // user.setLastName(userFromRq.getLastName());
        //user.setEmail(userFromRq.getEmail());
        //user.setPass(userFromRq.getPass());

        //users.add(user);
    }

    public List<User> getAll() {
        String jdbcURL = "jdbc:mysql://localhost:3306/twitter";
        String usernameConnection = "adrian.lupu";
        String passwordConnection = "password";
        users.clear();
        User user;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, usernameConnection, passwordConnection);
            Statement mystmt= connection.createStatement();
            String sql="select *from user";
            ResultSet rs=mystmt.executeQuery(sql);
            while(rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
                users.add(user);
            }
        }catch (SQLException |ClassNotFoundException ex) {
            // handle any errors
            user=new User();
            user.setFirstName(ex.getMessage());
            System.out.println("SQLException: {}" + ex.getMessage());
        }

        return users;

    }

    public User findByUserName(String userName) {
        String jdbcURL = "jdbc:mysql://localhost:3306/twitter";
        String usernameConnection = "adrian.lupu";
        String passwordConnection = "password";
        users.clear();
        User user;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, usernameConnection, passwordConnection);
            Statement mystmt= connection.createStatement();
            String sql="select *from user WHERE userName='"+userName+"'";
            ResultSet rs=mystmt.executeQuery(sql);
            if(rs.next()){
            user = new User(rs.getLong("id"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
            users.add(user);
            return users.stream()
                    .findFirst()
                    .orElse(null);}
        }
        catch (SQLException |ClassNotFoundException ex) {
            // handle any errors
            user=new User();
            user.setFirstName(ex.getMessage());
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        user=new User();
        return user;
    }
    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("user", id));
    }

}

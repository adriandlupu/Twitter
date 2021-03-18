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
            user.setUserName(ex.getMessage().toString());
            System.out.println("SQLException: {}" + ex.getMessage());
        }


        user.setId((long) users.size() + 1);

        user.setFirstName("no");
        user.setLastName(userFromRq.getLastName());
        user.setEmail(userFromRq.getEmail());
        user.setPass(userFromRq.getPass());

        users.add(user);
    }

    public List<User> getAll() {
        return users;

    }

    public User findByUserName(String userName) {
        return users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
    }

}

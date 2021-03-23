package org.fiipractic.service;

import org.fiipractic.config.DbConnection;
import org.fiipractic.model.User;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.fiipractic.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserService {

    private final List<User> users = new ArrayList<>();
    Connection con= DbConnection.getConnection();
    public void create(User userFromRq) {
        //User user = new User();
        try {
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into user (userName,firstName,lastName,email,password) values ('" + userFromRq.getUserName() + "','" + userFromRq.getFirstName() + "','" + userFromRq.getLastName() + "','" + userFromRq.getEmail() + "','" + userFromRq.getPass() + "')");
            //String sql="insert into user (userName,firstName) values(?,?)";
            //jdbcTemplateObject.update(sql,userFromRq.getUserName(),userFromRq.getFirstName());
            //return;
        }catch (SQLException e){
            System.out.println(e.getMessage());}
    }

    public List<User> getAll() {
        users.clear();
        User user;
        try{
            Statement mystmt= con.createStatement();
            String sql="select *from user";
            ResultSet rs=mystmt.executeQuery(sql);
            while(rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
                users.add(user);
            }
        }catch (SQLException ex) {
            // handle any errors
            user=new User();
            user.setFirstName(ex.getMessage());
            System.out.println("SQLException: {}" + ex.getMessage());
        }

        return users;
    }

    public User findByUserName(String userName) {
        users.clear();
        User user;
        try{
            Statement mystmt= con.createStatement();
            String sql="select *from user WHERE userName='"+userName+"'";
            ResultSet rs=mystmt.executeQuery(sql);
            if(rs.next()){
            user = new User(rs.getLong("id"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
            users.add(user);
            return users.stream()
                    .findFirst()
                    .orElse(null);}
        }
        catch (SQLException ex) {
            // handle any errors
            user=new User();
            user.setFirstName(ex.getMessage());
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        user=new User();
        return user;
    }
    public User findById(Long id) {
        users.clear();
        User user;
        try{
            Statement mystmt= con.createStatement();
            String sql="select *from user WHERE id='"+id+"'";
            ResultSet rs=mystmt.executeQuery(sql);
            if(rs.next()){
                user = new User(rs.getLong("id"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
                users.add(user);
                return users.stream()
                        .findFirst()
                        .orElseThrow(() -> new NotFoundException("user", id));}
        }
        catch (SQLException ex) {
            // handle any errors
            user=new User();
            user.setFirstName(ex.getMessage());
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        user=new User();
        return user;
    }

}
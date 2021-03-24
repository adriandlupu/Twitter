package org.fiipractic.repository;

import org.fiipractic.config.DbConnection;
import org.fiipractic.model.Post;
import org.fiipractic.model.Reply;
import org.fiipractic.model.User;
import org.fiipractic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReplyRepository {

    private List<Reply> replys = new ArrayList<>();

    Connection con= DbConnection.getConnection();

    public void addReply(Reply reply){
        try{
            Post post=reply.getParent();
            User user= post.getAuthor();
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into reply (postId, visible, message, timestamp, userId) values ('"+post.getId()+"','"+reply.isVisible()+"','"+reply.getMessage()+"','"+reply.getTimestamp()+"','"+user.getId()+"')");
        }catch(SQLException e){
            System.out.println(e.getMessage());}
    }
}

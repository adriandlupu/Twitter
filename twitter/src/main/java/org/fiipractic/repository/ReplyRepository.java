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
import java.util.Collections;
import java.util.List;

@Repository
public class ReplyRepository {

    Connection con= DbConnection.getConnection();

    public void addReply(Reply reply){
        try{
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into reply (postId, visible, message, timestamp, userId) values ('"+reply.getParentId()+"','"+(byte)(reply.isVisible()?1:0)+"','"+reply.getMessage()+"','"+reply.getTimestamp()+"','"+reply.getAuthorId()+"')");
        }catch(SQLException e){
            System.out.println(e.getMessage());}
    }

    public List<Reply> findReplysOfAPost(long id){
        List<Reply> replies= new ArrayList<>();
        Reply reply;
        try{
            Statement mystmt= con.createStatement();
            String sql="select *from reply WHERE postId='"+id+"'";
            System.out.println(id);
            ResultSet rs=mystmt.executeQuery(sql);
            while(rs.next()){
                reply=new Reply();

                reply.setParentId(rs.getLong("postId"));
                reply.setAuthorId(rs.getLong("userId"));
                reply.setMessage(rs.getString("message"));
                reply.setTimestamp(rs.getLong("timestamp"));
                reply.setId(rs.getLong("id"));
                reply.setVisible(rs.getBoolean("visible"));
                replies.add(reply);
            }

        }catch (SQLException ex){

            System.out.println("SQLException: {}" + ex.getMessage());
        }
        System.out.println(replies);
        return replies;
    }

    public Long deleteReplyOfAPost(long id) {
        try {
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("delete from reply where postId='" + id + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
}

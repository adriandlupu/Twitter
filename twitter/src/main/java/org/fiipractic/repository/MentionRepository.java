package org.fiipractic.repository;

import org.fiipractic.config.DbConnection;
import org.fiipractic.model.Mention;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class MentionRepository {
    Connection con= DbConnection.getConnection();

    public void addMention(Mention mention) {
        try{
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into mention (userId,postId) values ('"+mention.getUserId()+"','"+mention.getPostId()+"')");
        }catch(SQLException e){
            System.out.println(e.getMessage());}
    }

    public Long deleteMention(long userId, long postId) {
        try {
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("delete from mention where userId='" + userId +"'AND postId='"+postId+ "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return postId;
    }

    public Long deleteAllMentionsOfAPost(long postId) {
        try {
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("delete from mention where postId='"+postId+ "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return postId;
    }

}

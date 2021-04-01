package org.fiipractic.repository;

import org.fiipractic.config.DbConnection;
import org.fiipractic.model.Like;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class LikeRepository {

    Connection con= DbConnection.getConnection();

    public void addLike(Like like) {
        try{
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into alike (userId,postId) values ('"+like.getUserId()+"','"+like.getPostId()+"')");
        }catch(SQLException e){
            System.out.println(e.getMessage());}
    }

    public Long deleteLike(long userId, long postId) {
        try {
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("delete from alike where userId='" + userId +"'AND postId='"+postId+ "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return postId;
    }

}

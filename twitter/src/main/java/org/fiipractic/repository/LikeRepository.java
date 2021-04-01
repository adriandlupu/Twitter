package org.fiipractic.repository;

import org.fiipractic.config.DbConnection;
import org.fiipractic.model.Like;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public Long deleteAllLikesOfAPost(long postId) {
        try {
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("delete from alike where postId='"+postId+ "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return postId;
    }

    public List<Like> findLikesOfAPost(long id){
        List<Like> likes=new ArrayList<>();
        Like like;
        try {
            Statement mystmt = con.createStatement();
            String sql = "select *from alike WHERE postId='" + id + "'";
            System.out.println(id);
            ResultSet rs = mystmt.executeQuery(sql);
            while (rs.next()) {
                like=new Like();
                like.setPostId(rs.getLong("postId"));
                like.setUserId(rs.getLong("userId"));
                like.setId(rs.getLong("id"));
                likes.add(like);
            }
        }catch (SQLException ex){

            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return likes;
    }

}

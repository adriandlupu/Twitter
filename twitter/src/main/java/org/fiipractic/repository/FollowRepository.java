package org.fiipractic.repository;

import org.fiipractic.config.DbConnection;
import org.fiipractic.model.Follow;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class FollowRepository {
    Connection con= DbConnection.getConnection();

    public void addFollow(Follow follow){
        try{
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into follow (follower, following, timestamp) values ('"+follow.getFollowerId()+"','"+follow.getFollowingId()+"','"+follow.getTimestamp()+"')");
        }catch(SQLException e){
            System.out.println(e.getMessage());}
    }

    public Long deleteFollow(long followerId, long followingId) {
        try {
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("delete from follow where follower='" + followerId +"'AND following='"+followingId+ "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return followerId;
    }
}

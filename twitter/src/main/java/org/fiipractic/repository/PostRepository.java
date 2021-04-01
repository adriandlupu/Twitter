package org.fiipractic.repository;

import org.fiipractic.config.DbConnection;
import org.fiipractic.exception.NotFoundException;
import org.fiipractic.model.Post;
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
public class PostRepository {


    public void addPost(Post post) {
        try {
            Long authorId = post.getAuthorId();
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into post (userId,message,timestamp) values('" + authorId + "','" + post.getMessage() + "','" + post.getTimestamp() + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    Connection con = DbConnection.getConnection();

    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        Post post;
        try {
            Statement mystmt = con.createStatement();
            String sql = "select *from post";
            ResultSet rs = mystmt.executeQuery(sql);
            while (rs.next()) {
                post = new Post();
                post.setMessage(rs.getString("message"));
                post.setAuthorId(rs.getLong("userId"));
                post.setTimestamp(rs.getLong("timestamp"));
                post.setId(rs.getLong("id"));
                posts.add(post);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return posts;
    }

    public List<Post> getOwnPosts(long id) {
        List<Post> posts = new ArrayList<>();
        Post post;
        try {
            Statement mystmt = con.createStatement();
            String sql = "select *from post";
            ResultSet rs = mystmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getLong("userId") == id) {
                    post = new Post();
                    UserService userService = new UserService();
                    post.setMessage(rs.getString("message"));
                    post.setAuthorId(rs.getLong("userId"));
                    post.setTimestamp(rs.getLong("timestamp"));

                    post.setId(rs.getLong("id"));
                    posts.add(post);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return posts;
    }

    public List<Post> getOwnPosts(long id, long timestamp) {
        List<Post> posts = new ArrayList<>();
        Post post;
        try {
            Statement mystmt = con.createStatement();
            String sql = "select *from post";
            ResultSet rs = mystmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getLong("userId") == id && rs.getLong("timestamp") > timestamp) {
                    post = new Post();
                    UserService userService = new UserService();
                    post.setMessage(rs.getString("message"));
                    post.setAuthorId(rs.getLong("userId"));
                    post.setTimestamp(rs.getLong("timestamp"));
                    post.setId(rs.getLong("id"));
                    posts.add(post);
                }
            }
        } catch (SQLException ex) {

            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return posts;
    }

    public List<Long> getOwnPostsId(long id){
        List<Long> ids=new ArrayList<>();
        try {
            Statement mystmt = con.createStatement();
            String sql = "select * from post";
            ResultSet rs = mystmt.executeQuery(sql);
            while (rs.next()) {
                if(rs.getLong("userId")==id){
                    ids.add(rs.getLong("id"));
                    System.out.println(rs.getLong("id"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        System.out.println(ids);
        return ids;
    }

    public Post findPostById(long id) {
        Post post = null;
        try {
            Statement mystmt = con.createStatement();
            String sql = "select *from post WHERE id='" + id + "'";
            ResultSet rs = mystmt.executeQuery(sql);
            if (rs.next()) {
                post = new Post();
                post.setId(id);
                post.setMessage(rs.getString("message"));
                post.setAuthorId(rs.getLong("userId"));
                post.setTimestamp(rs.getLong("timestamp"));

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return post;
    }

    public Long deletePost(long id) {
        try {
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("delete from post where id='" + id + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
}

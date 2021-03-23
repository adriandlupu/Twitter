package org.fiipractic.repository;

import org.fiipractic.config.DbConnection;
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

    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        try {
            User user= post.getAuthor();
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into post (userId,message,timestamp) values('"+user.getId()+"','"+post.getMessage()+"','"+post.getTimestamp()+"')");}
        catch (SQLException e){
            System.out.println(e.getMessage());}
        posts.add(post);
    }

    public Long generatePostId() {
        return posts.size() + 1l;
    }
    Connection con= DbConnection.getConnection();
    public List<Post> getPosts() {
        posts.clear();
        Post post;
        try{
            Statement mystmt= con.createStatement();
            String sql="select *from post";
            ResultSet rs=mystmt.executeQuery(sql);
            while(rs.next()) {
                post = new Post();
                UserService userService=new UserService();
                post.setMessage(rs.getString("message"));
                post.setAuthor(userService.findById(rs.getLong("userId")));
                post.setTimestamp(rs.getLong("timestamp"));
                //post.setReplies();
                post.setId(rs.getLong("id"));
                posts.add(post);
            }
        }catch (SQLException ex) {
            post=new Post();
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return posts;
    }
}

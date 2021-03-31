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

    private List<Post> posts = new ArrayList<>();
    @Autowired
    private UserService userService;
    private ReplyRepository replyRepository;

    public void addPost(Post post) {
        try {
            User user= post.getAuthor();
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into post (userId,message,timestamp) values('"+user.getId()+"','"+post.getMessage()+"','"+post.getTimestamp()+"')");}
        catch (SQLException e){
            System.out.println(e.getMessage());}
        posts.add(post);
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

    public List<Post> getOwnPosts(long id) {
        posts.clear();
        Post post;
        try{
            Statement mystmt= con.createStatement();
            String sql="select *from post";
            ResultSet rs=mystmt.executeQuery(sql);
            while(rs.next()) {
                if(rs.getLong("userId")==id){
                post = new Post();
                UserService userService=new UserService();
                post.setMessage(rs.getString("message"));
                post.setAuthor(userService.findById(rs.getLong("userId")));
                post.setTimestamp(rs.getLong("timestamp"));
                post.setReplies(replyRepository.findReplysOfAPost(rs.getLong("id")));
                post.setId(rs.getLong("id"));
                posts.add(post);}
            }
        }catch (SQLException ex){
            post=new Post();
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return posts;
    }
    public List<Post> getOwnPosts(long id,long timestamp) {
        posts.clear();
        Post post;
        try{
            Statement mystmt= con.createStatement();
            String sql="select *from post";
            ResultSet rs=mystmt.executeQuery(sql);
            while(rs.next()) {
                if(rs.getLong("userId")==id&&rs.getLong("timestamp")>timestamp){
                    post = new Post();
                    UserService userService=new UserService();
                    post.setMessage(rs.getString("message"));
                    post.setAuthor(userService.findById(rs.getLong("userId")));
                    post.setTimestamp(rs.getLong("timestamp"));
                    post.setReplies(replyRepository.findReplysOfAPost(rs.getLong("id")));
                    post.setId(rs.getLong("id"));
                    posts.add(post);}
            }
        }catch (SQLException ex){
            post=new Post();
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return posts;
    }

    public Post findPostById(long id){
        posts.clear();
        Post post;
        try{
            Statement mystmt= con.createStatement();
            String sql="select *from post WHERE id='"+id+"'";
            ResultSet rs=mystmt.executeQuery(sql);
            if(rs.next()){
                post=new Post();
                post.setId(id);
                post.setMessage(rs.getString("message"));
                post.setAuthor(userService.findById(rs.getLong("userId")));
                post.setTimestamp(rs.getLong("timestamp"));
                //TO DO CONTINUA FUNCTIA FINDPOSTBYID CAND POTI, TOTUSI TREBUIE SA TE INTORCI LA
                //REPLYSERVICE CA SA FACI FUNCTIA FINDREPLYSOFAPOST OR SOME SHIT
                //post.setReplies(replyRepository.findReplysOfAPost(id));
                posts.add(post);
                return posts.stream()
                        .findFirst()
                        .orElseThrow(() -> new NotFoundException("post", id));
            }
        }catch(SQLException ex){
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        post=new Post();
        return post;
    }

    public Long deletePost(long id) {
        try {
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("delete from post where id='"+id+"'");}
        catch (SQLException e){
            System.out.println(e.getMessage());}
        return id;
    }
}

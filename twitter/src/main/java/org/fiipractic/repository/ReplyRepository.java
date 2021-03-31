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

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    Connection con= DbConnection.getConnection();

    public void addReply(Reply reply){
        try{
            Post post=reply.getParent();
            User user= reply.getAuthor();
            Statement mystmt = con.createStatement();
            mystmt.executeUpdate("insert into reply (postId, visible, message, timestamp, userId) values ('"+post.getId()+"','"+(byte)(reply.isVisible()?1:0)+"','"+reply.getMessage()+"','"+reply.getTimestamp()+"','"+user.getId()+"')");
        }catch(SQLException e){
            System.out.println(e.getMessage());}
    }

    public List<Reply> findReplysOfAPost(long id){
        replys.clear();
        Reply reply;
        try{
            Statement mystmt= con.createStatement();
            String sql="select *from reply WHERE postId='"+id+"'";
            ResultSet rs=mystmt.executeQuery(sql);
            while(rs.next()){
                reply=new Reply();
                //TO DO increfuckindibil, trebuie sa faci findPostById
                reply.setParent(postRepository.findPostById(id));
                reply.setAuthor(userService.findById(rs.getLong("userId")));
                reply.setMessage(rs.getString("message"));
                reply.setTimestamp(rs.getLong("timestamp"));
                reply.setId(rs.getLong("id"));
                reply.setVisible(rs.getBoolean("visible"));
                replys.add(reply);
            }
        }catch (SQLException ex){
            reply=new Reply();
            System.out.println("SQLException: {}" + ex.getMessage());
        }
        return replys;
    }
}

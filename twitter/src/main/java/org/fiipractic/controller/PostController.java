package org.fiipractic.controller;

import org.fiipractic.dto.PostDTO;
import org.fiipractic.dto.ReplyDTO;
import org.fiipractic.model.Post;
import org.fiipractic.service.PostService;
import org.fiipractic.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createPost(@RequestBody PostDTO post) {
        return postService.createPost(post);
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/reply")
    public Long createReply(@RequestBody ReplyDTO reply) {
        return replyService.createReply(reply);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping("/user")
    public List<Post> getOwnPosts(long id){return postService.getOwnPosts(id);}

    @RequestMapping("/userT")
    public List<Post> getOwnPosts(long id,long timestamp){return postService.getOwnPosts(id,timestamp);}

    @RequestMapping("/delete")
    public Long deletePost(long id){return postService.deletePost(id);}

}

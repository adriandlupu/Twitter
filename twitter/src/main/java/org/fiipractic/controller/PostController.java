package org.fiipractic.controller;

import org.fiipractic.dto.CreatePostDTO;
import org.fiipractic.dto.PostDTO;
import org.fiipractic.model.Post;
import org.fiipractic.service.PostService;
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

    @RequestMapping("/create")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createPost(@RequestBody CreatePostDTO post) {
        return postService.createPost(post);
    }

    @RequestMapping("/getposts")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping("/user")
    public List<PostDTO> getOwnPosts(long id){return postService.getOwnPosts(id);}

    @RequestMapping("/userT")
    public List<PostDTO> getOwnPosts(long id,long timestamp){return postService.getOwnPosts(id,timestamp);}

    @RequestMapping("/delete")
    public Long deletePost(long id){return postService.deletePost(id);}

    @RequestMapping("/copy")
    public void copyPost(long postId, long userId){ postService.copyPost(postId,userId);}

    @RequestMapping("/mentions")
    public List<PostDTO> getMentionPosts(long id) {
        return postService.getMentionPosts(id);
    }

    @RequestMapping("/feed")
    public List<PostDTO> getFeedPosts(long id) {
        return postService.getFeedPosts(id);
    }

}

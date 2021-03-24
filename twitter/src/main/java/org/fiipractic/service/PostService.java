package org.fiipractic.service;

import org.fiipractic.dto.PostDTO;
import org.fiipractic.model.Post;
import org.fiipractic.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public Long createPost(PostDTO postDTO) {

        Post post = new Post();
        post.setId(postRepository.generatePostId());
        post.setMessage(postDTO.getMessage());
        post.setAuthor(userService.findById(postDTO.getAuthorId()));
        post.setTimestamp(System.currentTimeMillis());
        postRepository.addPost(post);

        return post.getId();
    }

    public List<Post> getAllPosts() {
        return postRepository.getPosts();
    }

    public List<Post> getOwnPosts(long id) {
        return postRepository.getOwnPosts(id);
    }

    public List<Post> getOwnPosts(long id,long timestamp) {
        return postRepository.getOwnPosts(id,timestamp);
    }

    public Long deletePost(long id) {
        return postRepository.deletePost(id);
    }
}

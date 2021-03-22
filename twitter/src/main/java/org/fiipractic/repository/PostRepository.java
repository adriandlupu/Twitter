package org.fiipractic.repository;

import org.fiipractic.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {

    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
    }

    public Long generatePostId() {
        return posts.size() + 1l;
    }

    public List<Post> getPosts() {
        return posts;
    }
}

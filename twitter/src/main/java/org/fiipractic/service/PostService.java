package org.fiipractic.service;

import org.fiipractic.dto.CreatePostDTO;
import org.fiipractic.dto.PostDTO;
import org.fiipractic.model.Post;
import org.fiipractic.repository.LikeRepository;
import org.fiipractic.repository.MentionRepository;
import org.fiipractic.repository.PostRepository;
import org.fiipractic.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private MentionService mentionService;

    public Long createPost(CreatePostDTO createPostDTO) {

        Post post = new Post();
        post.setMessage(createPostDTO.getMessage());
        post.setAuthorId(createPostDTO.getAuthorId());
        post.setTimestamp(System.currentTimeMillis());
        postRepository.addPost(post);

        return post.getId();
    }

    public List<PostDTO> getAllPosts() {

        List<Post> posts = postRepository.getPosts();
        return posts.stream()
                .map(post -> toDTO(post, true))
                .collect(Collectors.toList());
    }

    public List<PostDTO> getOwnPosts(long id) {
        List<Post> posts = postRepository.getOwnPosts(id);
        return posts.stream()
                .map(post -> toDTO(post, false))
                .collect(Collectors.toList());
    }

    public List<PostDTO> getMentionPosts(long id) {
        List<Post> posts = postRepository.getMentionPosts(id);
        return posts.stream()
                .map(post -> toDTO(post, true))
                .collect(Collectors.toList());
    }

    public List<PostDTO> getFeedPosts(long id) {
        List<Post> posts = postRepository.getFeedPosts(id);
        return posts.stream()
                .map(post -> toDTO(post, true))
                .collect(Collectors.toList());
    }

    public List<PostDTO> getOwnPosts(long id, long timestamp) {
        List<Post> posts = postRepository.getOwnPosts(id, timestamp);
        return posts.stream()
                .map(post -> toDTO(post, false))
                .collect(Collectors.toList());
    }

    public Long deletePost(long id) {
        return postRepository.deletePost(id)+replyService.deleteReplyOfAPost(id)+likeService.deleteAllLikesOfAPost(id)-mentionService.deleteAllMentionsOfAPost(id);

    }

    public void copyPost(Long postId, Long userId){
        Post post=postRepository.findPostById(postId);
        post.setAuthorId(userId);
        post.setTimestamp(System.currentTimeMillis());
        postRepository.addPost(post);
    }


    public List<Long> getOwnPostsId(long id){return postRepository.getOwnPostsId(id);}

    private PostDTO toDTO(Post post, boolean includeAuthor) {

        PostDTO postDTO = new PostDTO();
        postDTO.setMessage(post.getMessage());
        if (includeAuthor) {
            postDTO.setAuthor(userService.findById(post.getAuthorId()));
        }
        postDTO.setReplies(replyService.getRepliesByPostId(post.getId()));
        postDTO.setLikes(likeService.getLikesOfAPost(post.getId()));
        return postDTO;

    }
}

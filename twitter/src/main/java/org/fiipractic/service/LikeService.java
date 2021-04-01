package org.fiipractic.service;

import org.fiipractic.model.Like;
import org.fiipractic.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public Long createLike(Like createLike){
        Like like= new Like();
        like.setUserId(createLike.getUserId());
        like.setPostId(createLike.getPostId());
        likeRepository.addLike(like);
        return like.getUserId();
    }

    public Long deleteLike(long userId, long postId) {
        return likeRepository.deleteLike(userId, postId);
    }

}

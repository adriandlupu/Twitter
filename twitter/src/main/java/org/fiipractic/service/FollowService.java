package org.fiipractic.service;

import org.fiipractic.dto.CreateFollowDTO;
import org.fiipractic.model.Follow;
import org.fiipractic.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    public Long createFollow(CreateFollowDTO createFollowDTO){

        Follow follow=new Follow();
        follow.setFollowerId(createFollowDTO.getFollowerId());
        follow.setFollowingId(createFollowDTO.getFollowingId());
        follow.setTimestamp(System.currentTimeMillis());
        followRepository.addFollow(follow);
        return follow.getTimestamp();
    }
    public Long deleteFollow(long followerId, long followingId) {
        return followRepository.deleteFollow(followerId, followingId);
    }
}

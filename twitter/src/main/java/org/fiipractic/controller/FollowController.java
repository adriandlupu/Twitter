package org.fiipractic.controller;


import org.fiipractic.dto.CreateFollowDTO;
import org.fiipractic.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createFollow(@RequestBody CreateFollowDTO followDTO) {
        return followService.createFollow(followDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/delete")
    public Long deletePost(long followerId, long followingId){ return followService.deleteFollow(followerId,followingId);}
}

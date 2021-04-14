package org.fiipractic.controller;


import org.fiipractic.dto.CreateFollowDTO;
import org.fiipractic.model.Like;
import org.fiipractic.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @RequestMapping("/create")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createLike(@RequestBody Like like) {
        return likeService.createLike(like);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/delete")
    public Long deleteLike(long userId, long postId){ return likeService.deleteLike(userId,postId);}

}

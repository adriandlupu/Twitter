package org.fiipractic.controller;

import org.fiipractic.model.Like;
import org.fiipractic.model.Mention;
import org.fiipractic.service.LikeService;
import org.fiipractic.service.MentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mention")
public class MentionController {
    @Autowired
    private MentionService mentionService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createMention(@RequestBody Mention mention) {
        return mentionService.createMention(mention);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/delete")
    public Long deleteMention(long userId, long postId){ return mentionService.deleteMention(userId,postId);}
}

package org.fiipractic.controller;

import org.fiipractic.dto.ReplyDTO;
import org.fiipractic.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/replys")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createReply(@RequestBody ReplyDTO reply) {
        return replyService.createReply(reply);
    }


}

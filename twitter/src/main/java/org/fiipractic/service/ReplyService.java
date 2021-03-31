package org.fiipractic.service;

import org.fiipractic.dto.CreateReplyDTO;
import org.fiipractic.dto.ReplyDTO;
import org.fiipractic.model.Reply;
import org.fiipractic.repository.PostRepository;
import org.fiipractic.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserService userService;


    public Long createReply(CreateReplyDTO createReplyDTO) {

        Reply reply = new Reply();
        reply.setMessage(createReplyDTO.getMessage());
        reply.setAuthorId(createReplyDTO.getAuthorId());
        reply.setTimestamp(System.currentTimeMillis());
        reply.setParentId(createReplyDTO.getParentPostId());
        reply.setVisible(createReplyDTO.getVisible());
        replyRepository.addReply(reply);
        return reply.getTimestamp();
    }

    public List<ReplyDTO> getRepliesByPostId(Long id) {
        List<Reply> replies = replyRepository.findReplysOfAPost(id);


        return replies.stream().map(reply -> {
            ReplyDTO replyDTO = new ReplyDTO();
            replyDTO.setAuthor(userService.findById(reply.getAuthorId()));
            replyDTO.setMessage(reply.getMessage());
            replyDTO.setVisible(reply.isVisible());
            return replyDTO;
        }).collect(Collectors.toList());
    }

}

package org.fiipractic.service;

import org.fiipractic.dto.PostDTO;
import org.fiipractic.dto.ReplyDTO;
import org.fiipractic.model.Post;
import org.fiipractic.model.Reply;
import org.fiipractic.repository.PostRepository;
import org.fiipractic.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    public Long createReply(ReplyDTO replyDTO) {

        Reply reply = new Reply();
        reply.setMessage(replyDTO.getMessage());
        reply.setAuthor(userService.findById(replyDTO.getAuthorId()));
        reply.setTimestamp(System.currentTimeMillis());
        //TO DO: DUPA CE FAC FUNCTIA FINDPOSTBYID, O APELEZ AICI PENTRU A STI CARE E PARINTELE REPLY-ULUI
        reply.setParent(postRepository.findPostById(replyDTO.getParentPostId()));
        reply.setVisible(replyDTO.getVisible());
        replyRepository.addReply(reply);
        return reply.getTimestamp();
    }

}

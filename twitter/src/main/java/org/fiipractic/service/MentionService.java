package org.fiipractic.service;

import org.fiipractic.model.Like;
import org.fiipractic.model.Mention;
import org.fiipractic.repository.LikeRepository;
import org.fiipractic.repository.MentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentionService {

    @Autowired
    private MentionRepository mentionRepository;

    public Long createMention(Mention createMention){
        Mention mention= new Mention();
        mention.setUserId(createMention.getUserId());
        mention.setPostId(createMention.getPostId());
        mentionRepository.addMention(mention);
        return mention.getUserId();
    }

    public Long deleteMention(long userId, long postId) {
        return mentionRepository.deleteMention(userId, postId);
    }

    public Long deleteAllMentionsOfAPost(long postId) { return mentionRepository.deleteAllMentionsOfAPost(postId);}

    public Long deleteAllMentionsOfAUser(long postId) { return mentionRepository.deleteAllMentionsOfAUser(postId);}

}

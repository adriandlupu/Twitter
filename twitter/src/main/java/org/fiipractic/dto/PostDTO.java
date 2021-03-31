package org.fiipractic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.fiipractic.model.User;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@JsonInclude(NON_NULL)
public class PostDTO {
    private String message;
    private UserDTO author;
    private List<ReplyDTO> replies;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public List<ReplyDTO> getReplies() {
        return replies;
    }

    public void setReplies(List<ReplyDTO> replies) {
        this.replies = replies;
    }
}

package org.fiipractic.dto;

public class ReplyDTO {

    private Long parentPostId;
    private String message;
    private Long authorId;
    private Boolean visible;

    public Long getParentPostId() {
        return parentPostId;
    }

    public void setParentPostId(Long postId) {
        this.parentPostId = postId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
package org.fiipractic.model;

public class Reply {

    private Long parentId;
    private Long id;
    private String message;
    private Long authorId;
    private long timestamp;
    private boolean visible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "parentId=" + parentId +
                ", id=" + id +
                ", message='" + message + '\'' +
                ", authorId=" + authorId +
                ", timestamp=" + timestamp +
                ", visible=" + visible +
                '}';
    }
}

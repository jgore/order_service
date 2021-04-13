package pl.goreit.order_service.domain.model;

import java.util.Objects;

public class Comment {

    private Integer sequenceNo;
    private String userId;
    private String text;

    public Comment(Integer sequenceNo, String userId, String text) {
        this.sequenceNo = sequenceNo;
        this.userId = userId;
        this.text = text;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getUserId(), comment.getUserId()) &&
                Objects.equals(getText(), comment.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getText());
    }

}

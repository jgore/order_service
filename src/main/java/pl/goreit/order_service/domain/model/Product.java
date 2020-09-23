package pl.goreit.order_service.domain.model;

import com.google.common.collect.Lists;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.goreit.order_service.CategoryName;
import pl.goreit.order_service.DomainException;
import pl.goreit.order_service.ExceptionCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document
public class Product {

    @Id
    private String id;
    private String sellerId;
    private CategoryName categoryName;
    @Indexed(unique = true)
    private String title;
    private String text;
    private BigDecimal price;
    private Integer quantity;
    private List<Comment> comments;
    private Status status;

    private LocalDateTime creationDate;

    public Product(CategoryName categoryName, String title, String text, BigDecimal price, Integer quantity, String sellerId) {
        this.categoryName = categoryName;
        this.sellerId = sellerId;
        this.title = title;
        this.text = text;
        this.price = price;
        this.quantity = quantity;
        this.status = Status.ACTIVE;
        this.creationDate = LocalDateTime.now();
        this.comments = Lists.newArrayList();
    }

    public boolean addComment(Comment comment) throws DomainException {
        if (Status.ACTIVE != getStatus()) {
            throw new DomainException(ExceptionCode.GOREIT_03);
        }
        return comments.add(comment);
    }

    public String getSellerId() {
        return sellerId;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<Comment> getComments() {
        return Lists.newArrayList(comments);
    }


    public enum Status {
        ACTIVE, SOLD, ARCHIVED
    }
}

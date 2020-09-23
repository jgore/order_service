package pl.goreit.order_service.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Order {

    @Id
    private String id;

    private String userId;

    private String sellerId;

    private List<OrderLine> orderLines;

    private Boolean isInvoice;

    private Boolean isSummarizePdf;

    private Status status;


    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime creationTime;

    public Order() {
    }

    public Order(String id, String sellerId, String userId, List<OrderLine> orderLines, LocalDateTime creationTime) {
        this.id = id;
        this.sellerId = sellerId;
        this.userId = userId;
        this.orderLines = orderLines;
        this.creationTime = creationTime;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    enum Status {
        CREATED, REALIZED, WAITING_FOR_DELIVERY, CLOSED
    }
}
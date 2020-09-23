package pl.goreit.order_service.domain.model;

import java.math.BigDecimal;

public class OrderLine {

    private String orderId;
    private String sellerId;
    private String productTitle;
    private Integer amount;
    private BigDecimal price;

    public OrderLine() {
    }

    public OrderLine(String orderId, String sellerId, String productTitle, Integer amount, BigDecimal price) {
        this.orderId = orderId;
        this.sellerId = sellerId;
        this.productTitle = productTitle;
        this.amount = amount;
        this.price = price;

    }

    public String getSellerId() {
        return sellerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

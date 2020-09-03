package pl.goreit.order_service.domain.service;

import pl.goreit.order_service.DomainException;
import pl.goreit.order_service.domain.model.Order;
import pl.goreit.order_service.generated.CreateOrderRequest;

import java.util.List;

public interface OrderService {

    Order findById(String id) throws DomainException;
    List<Order> findByUserId(String userId) throws DomainException;

    Order create(CreateOrderRequest orderRequest) throws DomainException;
}

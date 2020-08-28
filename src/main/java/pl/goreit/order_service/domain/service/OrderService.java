package pl.goreit.order_service.domain.service;

import com.example.types.CreateOrderRequest;
import pl.goreit.order_service.DomainException;
import pl.goreit.order_service.domain.model.Order;

import java.util.List;

public interface OrderService {

    Order findById(String id) throws DomainException;
    List<Order> findByUserId(String userId) throws DomainException;

    Order create(CreateOrderRequest orderRequest) throws DomainException;
}

package pl.goreit.order_service.infrastructure.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import pl.goreit.order_service.domain.model.Order;

import java.util.List;

@Component
public interface OrderRepo extends MongoRepository<Order, String> {

    List<Order> findByUserId(String userId);

}

package pl.goreit.order_service.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.order_service.domain.model.Order;
import pl.goreit.order_service.generated.OrderResponse;
import pl.goreit.order_service.generated.OrderlineView;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter implements Converter<Order, OrderResponse> {

    @Override
    public OrderResponse convert(Order order) {

        return new OrderResponse(order.getId(),order.getCreationTime().toString(),  order.getOrderLines().stream()
                .map(orderProduct -> new OrderlineView(orderProduct.getProductTitle(), orderProduct.getAmount(), orderProduct.getPrice()))
                .collect(Collectors.toList()));
    }
}

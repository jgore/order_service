package pl.goreit.order_service.domain.service.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.goreit.order_service.DomainException;
import pl.goreit.order_service.domain.converter.OrderConverter;
import pl.goreit.order_service.domain.model.Order;
import pl.goreit.order_service.domain.service.OrderService;
import pl.goreit.order_service.generated.CreateOrderRequest;
import pl.goreit.order_service.generated.OrderResponse;

import static pl.goreit.order_service.configuration.RabbitConfig.CREATE_ORDER_Q_IN;
import static pl.goreit.order_service.configuration.RabbitConfig.CREATE_ORDER_Q_OUT;

@Component
public class MqOrderService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderConverter orderConverter;

    public MqOrderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendOrder(OrderResponse orderResponse) throws JsonProcessingException {
        String jsonOrderResponse = objectMapper.writeValueAsString((orderResponse));
        System.out.println("Sending -> " + jsonOrderResponse);
        rabbitTemplate.convertAndSend(CREATE_ORDER_Q_OUT, jsonOrderResponse);
    }

    @RabbitListener(queues = CREATE_ORDER_Q_IN)
    public void listen(String in) throws JsonProcessingException, DomainException {
        System.out.println("Message read from order_q : " + in);
        CreateOrderRequest createOrderRequest = objectMapper.readValue(in, CreateOrderRequest.class);

        Order order = orderService.create(createOrderRequest);

        OrderResponse orderResponse = orderConverter.convert(order);
        sendOrder(orderResponse);
    }

}

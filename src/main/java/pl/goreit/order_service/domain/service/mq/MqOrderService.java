package pl.goreit.order_service.domain.service.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.goreit.order_service.DomainException;
import pl.goreit.order_service.domain.model.Order;
import pl.goreit.order_service.domain.service.OrderService;
import pl.goreit.order_service.generated.CreateOrderRequest;

import static pl.goreit.order_service.configuration.RabbitConfig.CREATE_ORDER_Q_IN;
import static pl.goreit.order_service.configuration.RabbitConfig.CREATE_ORDER_Q_OUT;

@Component
public class MqOrderService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    public MqOrderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendOrder(Order order) throws JsonProcessingException {
        String jsonOrder = objectMapper.writeValueAsString((order));
        System.out.println("Sending -> " + jsonOrder);
        rabbitTemplate.convertAndSend(CREATE_ORDER_Q_OUT, jsonOrder);
    }

    @RabbitListener(queues = CREATE_ORDER_Q_IN)
    public void listen(String in) throws JsonProcessingException, DomainException {
        System.out.println("Message read from order_q : " + in);
        CreateOrderRequest createOrderRequest = objectMapper.readValue(in, CreateOrderRequest.class);

        Order order = orderService.create(createOrderRequest);
        sendOrder(order);
    }

}

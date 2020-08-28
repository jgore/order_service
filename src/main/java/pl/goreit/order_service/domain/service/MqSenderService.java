package pl.goreit.order_service.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.goreit.order_service.domain.model.Order;

import static pl.goreit.order_service.configuration.RabbitConfig.ORDER_Q;

@Component
public class MqSenderService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public MqSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendOrder(Order order) throws JsonProcessingException {
        String jsonOrder = objectMapper.writeValueAsString((order));
        System.out.println("Sending -> " + jsonOrder);
        rabbitTemplate.convertAndSend(ORDER_Q, jsonOrder);
    }
}

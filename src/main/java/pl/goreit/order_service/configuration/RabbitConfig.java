package pl.goreit.order_service.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final String CREATE_ORDER_Q_IN = "CREATE_ORDER_Q_IN";
    public static final String CREATE_ORDER_Q_OUT = "CREATE_ORDER_Q_OUT";

    @Bean
    public Queue CREATE_ORDER_Q_IN() {
        return new Queue(CREATE_ORDER_Q_IN, false);
    }


    @Bean
    public Queue CREATE_ORDER_Q_OUT() {
        return new Queue(CREATE_ORDER_Q_OUT, false);
    }

}
package pl.goreit.order_service.domain.service;

import org.bson.types.ObjectId;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.goreit.order_service.DomainException;
import pl.goreit.order_service.ExceptionCode;
import pl.goreit.order_service.domain.model.Order;
import pl.goreit.order_service.domain.model.OrderLine;
import pl.goreit.order_service.domain.model.Product;
import pl.goreit.order_service.domain.service.mq.MqOrderService;
import pl.goreit.order_service.generated.CreateOrderRequest;
import pl.goreit.order_service.generated.OrderLineRequest;
import pl.goreit.order_service.infrastructure.mongo.OrderRepo;
import pl.goreit.order_service.infrastructure.mongo.ProductRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private MqOrderService mqOrderService;

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public Order findById(String id) throws DomainException {
        return orderRepo.findById(id).orElseThrow(() -> new DomainException(ExceptionCode.GOREIT_04));
    }

    @Override
    public List<Order> findByUserId(String userId) {
        return orderRepo.findByUserId(userId);
    }

    @Override
    public Order create(CreateOrderRequest orderRequest) throws DomainException {
        ObjectId orderId = ObjectId.get();

        ProcessInstantiationBuilder createOrderProcessBuilder = runtimeService.createProcessInstanceByKey("create_order");
        createOrderProcessBuilder.setVariable("isInvoice", orderRequest.getIsInvoice());
        createOrderProcessBuilder.setVariable("isSummarizedPdf", orderRequest.getIsSummarizedPdf());
        createOrderProcessBuilder.execute();


        List<OrderLineRequest> orderLineRequests = orderRequest.getOrderlines();

        if (orderLineRequests == null || orderLineRequests.isEmpty()) {
            throw new DomainException(ExceptionCode.GOREIT_06);
        }

        List<OrderLine> orderlines = orderLineRequests.stream()
                .map(orderLineView -> {

                    //@FIXME get all upper
                    Product product = productRepo.findByTitle(orderLineView.getProductTitle()).get();
                    return new OrderLine(orderId.toString(), product.getTitle(), orderLineView.getAmount(), product.getPrice());
                })
                .collect(Collectors.toList());

        Order order = new Order(orderId.toString(), orderRequest.getUserId(), orderlines, LocalDateTime.now());
        orderRepo.save(order);

        return order;
    }
}


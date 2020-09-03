package pl.goreit.order_service.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.goreit.order_service.DomainException;
import pl.goreit.order_service.domain.model.Order;
import pl.goreit.order_service.domain.service.OrderService;
import pl.goreit.order_service.generated.CreateOrderRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/{id}")
    @ApiOperation(value = "get order by id")
    public Order getOrder(@PathVariable("id") String id) throws DomainException {
        return orderService.findById(id);
    }

    @GetMapping("/byUser/{id}")
    @ApiOperation(value="get by user id", authorizations=@Authorization(value="oauth2", scopes=@AuthorizationScope(description="write", scope="write")))
    public List<Order> getOrders(HttpServletRequest httpRequest, @PathVariable("id") String id) throws DomainException {

        return orderService.findByUserId(id);
    }


    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "add new order")
    public Order addOrder(@RequestBody CreateOrderRequest orderRequest) throws DomainException {
        return orderService.create(orderRequest);
    }
}

package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.data.OrderRepository;
import tacos.data.UserRepository;
import tacos.domain.Order;
import tacos.domain.User;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;

    private UserRepository userRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {

        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }

        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {

        if(errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}

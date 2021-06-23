package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.domain.Order;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {

        if(errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Order submitted: " + order);

        return "redirect:/";
    }
}

package com.mspark.tacos.web.controller;

import com.mspark.tacos.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("orders")
public class OrderController {

    private static final String ORDER_FORM_VIEW = "orderForm";

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return ORDER_FORM_VIEW;
    }

    @PostMapping
    public String processOrder(@Valid  Order order, Errors errors){
        if(errors.hasErrors()){
            return ORDER_FORM_VIEW;
        }

        log.info("Order submitted : " + order);

        return "redirect:/";
    }
}

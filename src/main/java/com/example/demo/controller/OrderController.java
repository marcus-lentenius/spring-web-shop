package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private ProductService productService;


    @GetMapping("order/new")
    public String newOrder() {
        User user = getUser();
        List<Cart> cartList = cartService.findAllByUserId(user.getId());

        UserOrder userOrder = new UserOrder();

        userOrder.setDate("datum");
        userOrder.setUserId(user.getId());
        userOrder.setShipped(false);
        userOrder.setOrderProduct(new ArrayList<>());

        cartList.forEach(cart -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setAmount(cart.getAmount());
            orderProduct.setProductId(cart.getProductId());
            orderProduct.setUserOrder(userOrder);

            userOrder.getOrderProduct().add(orderProduct);
            cartService.delete(cart);
        });

        userOrderService.save(userOrder);


        return "redirect:/";
    }

    @GetMapping("/user/orders/{id}")
    public ModelAndView showOrder(@PathVariable Long id) {
        UserOrder order = userOrderService.findById(id);
        List<OrderProduct> orderProducts = orderProductService.findByUserOrderId(id);

        Map<Product, Integer> products = new HashMap<>();

        orderProducts.forEach(orderProduct -> {
            products.put(productService.findById(orderProduct.getProductId()),
                    orderProduct.getAmount());
        });

        return new ModelAndView("user/orders")
                .addObject("order", order)
                .addObject("products", products);
    }

    private User getUser() {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUserName(userName);
    }
}

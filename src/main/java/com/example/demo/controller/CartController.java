package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/user/cart/increment/{id}")
    public String incrementProduct(@PathVariable Long id) {
        User user = getUser();
        List<Cart> cartList = cartService.findAllByUserId(user.getId());

        cartList.forEach(cart -> {
            if (cart.getProductId().equals(id)) {
                cart.setAmount(cart.getAmount() + 1);
                cartService.save(cart);
            }
        });

        return "redirect:/user/cart";
    }

    @GetMapping("/user/cart/decrement/{id}")
    public String decrementProduct(@PathVariable Long id) {
        User user = getUser();
        List<Cart> cartList = cartService.findAllByUserId(user.getId());

        cartList.forEach(cart -> {
            if (cart.getProductId().equals(id)) {
                cart.setAmount(cart.getAmount() - 1);
                cartService.save(cart);
            }
        });

        return "redirect:/user/cart";
    }

    @GetMapping("/user/cart/delete/{id}")
    public String delete(@PathVariable Long id) {
        User user = getUser();
        Cart cart = cartService.findByUserIdAndProductId(user.getId(), id);
        cartService.delete(cart);
        return "redirect:/user/cart";
    }
//todo map to /user
    @PostMapping("/cart/add")
    public ModelAndView addProduct(@RequestParam String amount, @RequestParam String id) {


        //todo improve error handling
        try {
            User user = getUser();
            Product product = productService.findById(Long.parseLong(id));
            Cart cart;

            if (cartService.findByUserIdAndProductId(user.getId(), product.getId()) == null) {
                cart = new Cart();

                cart.setProductId(product.getId());
                cart.setAmount(Integer.parseInt(amount));
                cart.setUserId(user.getId());
            } else {
                cart = cartService.findByUserIdAndProductId(user.getId(), product.getId());

                cart.setAmount(cart.getAmount() + Integer.parseInt(amount));
            }

            cartService.save(cart);

            return new ModelAndView("redirect:/");
        } catch (NullPointerException e) {
            e.printStackTrace();
//todo make simple 404 and add validation
            return new ModelAndView("error")
                    .addObject("errorMessage", "Wrong input, use numbers/login");
        }
    }

    @GetMapping("/user/cart")
    public ModelAndView showCart() {
        User user = getUser();

        Map<Product, Integer> cart = new HashMap<>();

        cartService.findAllByUserId(user.getId()).forEach(entity -> {
            cart.put(
                    productService.findById(entity.getProductId()),
                    entity.getAmount()
            );
        });

        AtomicInteger totalPrice = new AtomicInteger();

        cart.forEach((product, amount) -> {
            totalPrice.addAndGet(product.getPrice() * amount);
        });

        return new ModelAndView("user/cart")
                .addObject("cart", cart)
                .addObject("totalPrice", totalPrice);
    }

    //todo duplicate
    // need error handling
    private User getUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUserName(userName);
    }
}

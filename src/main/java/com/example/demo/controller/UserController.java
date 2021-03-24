package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserOrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("/my-account")
    public ModelAndView showAccount() {
        User user = getUser();
        List<UserOrder> userOrders = userOrderService.findByUserId(user.getId());

        return new ModelAndView("/user/my-account")
                .addObject("user", user)
                .addObject("orderList", userOrders);
    }

    @GetMapping("/update-account")
    public ModelAndView updateAccount() {
        User user = getUser();

        return new ModelAndView("/user/update-account")
                .addObject("user", user);
    }
    @PostMapping("/update")
    public String saveUpdate(@ModelAttribute("user") User userUpdate) {
        User user = getUser();

        user.setCity(userUpdate.getCity());
        user.setPhoneNumber(userUpdate.getPhoneNumber());
        user.setAddress(userUpdate.getAddress());
        user.setPassword(userService.encryptedPassword(userUpdate));
        user.setZipCode(userUpdate.getZipCode());

        userService.save(user);

        return "redirect:/user/my-account";
    }

    private User getUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUserName(userName);
    }
}

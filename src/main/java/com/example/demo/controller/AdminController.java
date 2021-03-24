package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    //todo Field injection is not recommended
    @Autowired
    private ProductService productService;

    @GetMapping("/add-product")
    public String createProduct(Model product, Model title) {
        product.addAttribute("product", new Product());
        title.addAttribute("title", "Add product");

        return "admin/add-product";
    }

    //todo add validation
    @PostMapping("/saveProduct")
    public String saveUser(@ModelAttribute("product") Product product) {
        productService.save(product);

        return "login";
    }
}

package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    private ModelAndView showProduct(@PathVariable Long id) {

        Product product = productService.findById(id);

        return new ModelAndView("product/product")
                .addObject("product", product);
    }

    @PostMapping("/product/find-product-by-name")
    public String searchProduct(@RequestParam String searchProductName) {

        Product product = productService.findByName(searchProductName);

        return "redirect:/product/" + product.getId();
    }
}

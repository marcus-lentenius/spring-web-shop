package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GeneralController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public ModelAndView showIndexPage() {

        List<String> categoryList = new ArrayList<>();
        List<Product> productList;

        try {
            productList = (List<Product>) productRepository.findAll();

            productList.forEach(product -> {
                if (!categoryList.contains(product.getCategory())) {
                    categoryList.add(product.getCategory());
                }
            });

            var model = new ModelAndView("index");

            model.addObject("categoryList", categoryList);
            model.addObject("productList", productList);

            return model;

        } catch (Exception e) {
            //todo what error?
            e.printStackTrace();

            var model = new ModelAndView("error");

            model.addObject("error", "Failed to find products");

            return model;
        }
    }
}

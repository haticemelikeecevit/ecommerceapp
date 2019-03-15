package com.example.ecommerce.controllers;

import com.example.ecommerce.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String listProducts(Model model){
        model.addAttribute("categories", categoryService.listAllCategories());
        return "categories";
    }
}
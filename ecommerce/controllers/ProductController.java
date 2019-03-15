package com.example.ecommerce.controllers;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.services.CategoryService;
import com.example.ecommerce.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public String listProducts(Model model){
        model.addAttribute("products",productService.listAllProducts());
        return "products";
    }

    @GetMapping("/products/category")
    public String getProductsByCategory(@RequestParam Long categoryId, Model model) {
        model.addAttribute("productsByCategory",productService.getProductByCategoryId(categoryId));
        return "productsByCategory";
    }

    @RequestMapping("/products/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.listAllCategories());
        return "productCreate";
    }

    @PostMapping(value = "products/create")
    public String saveProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("products/edit")
    public String edit(@RequestParam Long id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.listAllCategories());
        return "productCreate";
    }

    @GetMapping("/products/delete")
    public String delete(@RequestParam Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}

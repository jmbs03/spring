package com.example.controller;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    /*
    GET http://localhost:8080/categories
     */
    @GetMapping
    public String findAll(Model model){

        List<Category> categories = this.repository.findAll();
        model.addAttribute("categories", categories);
        return "category-list";
    }
/*
    /*
    GET http://localhost:8080/products/new
     */
    @GetMapping("/new")
    public String getForm(Model model){
        model.addAttribute("products", new Product());
        return "product-form";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        Optional<Category> category = repository.findById(id);

        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "category-edit"; // Nombre del archivo HTML en templates
        } else {
            redirectAttributes.addFlashAttribute("message", "Categoría no encontrada");
            redirectAttributes.addFlashAttribute("alert", "warning");
            return "redirect:/categories"; // Redirigir si no se encuentra el product
        }
    }
    /*
    POST http://localhost:8080/products/{id}/edit
     */
    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes){

        this.repository.save(category);
        redirectAttributes.addFlashAttribute("message", "Categoría modificada con éxito");
        redirectAttributes.addFlashAttribute("alert", "success");
        return "redirect:/categories";
    }

}

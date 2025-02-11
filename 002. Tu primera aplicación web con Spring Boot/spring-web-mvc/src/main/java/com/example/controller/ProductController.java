package com.example.controller;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {


    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    /*
    GET http://localhost:8080/products
     */
    @GetMapping
    public String findAll(Model model){

        List<Product> products = this.repository.findAll();
        model.addAttribute("products", products);
        return "product-list";
    }

    /*
    GET http://localhost:8080/products/new
     */
    @GetMapping("/new")
    public String getForm(Model model){
        model.addAttribute("products", new Product());
        return "product-form";
    }

    /*
    POST http://localhost:8080/products
     */
    @PostMapping
    public String save(@ModelAttribute("product") Product product){
        this.repository.save(product);
        return "redirect:/products";
    }

    /*
    GET http://localhost:8080/products/{id}/view
     */
    @GetMapping("/{id}/view")
    public String getProduct(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-view"; // Nombre del archivo HTML en templates
        } else {
            redirectAttributes.addFlashAttribute("message", "Producto no encontrado");
            redirectAttributes.addFlashAttribute("alert", "warning");
            return "redirect:/products"; // Redirigir si no se encuentra el product
        }
    }
        /*
    GET http://localhost:8080/products/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-edit"; // Nombre del archivo HTML en templates
        } else {
            redirectAttributes.addFlashAttribute("message", "Producto no encontrado");
            redirectAttributes.addFlashAttribute("alert", "warning");
            return "redirect:/products"; // Redirigir si no se encuentra el product
        }
    }
    /*
    POST http://localhost:8080/products/{id}/edit
     */
    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes){
        this.repository.save(product);
        redirectAttributes.addFlashAttribute("message", "Producto modificado con éxito");
        redirectAttributes.addFlashAttribute("alert", "success");
        return "redirect:/products";
    }
            /*
    GET http://localhost:8080/products/{id}/delete
     */
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-delete"; // Nombre del archivo HTML en templates
        } else {
            redirectAttributes.addFlashAttribute("message", "Producto no encontrado");
            redirectAttributes.addFlashAttribute("alert", "warning");
            return "redirect:/products"; // Redirigir si no se encuentra el product
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes){
        this.repository.delete(product);
        redirectAttributes.addFlashAttribute("message", "Producto eliminado con éxito");
        redirectAttributes.addFlashAttribute("alert", "success");
        return "redirect:/products";
    }
    /*
    GET http://localhost:8080/products/delete/all
     */
    @GetMapping("/delete/all")
    public String deleteAllQuest(Model model){
        // Aquí se podrían validar los permisos de borrado del usuario, por ejemplo.
        model.addAttribute("total", this.repository.count());
        return "product-delete-all";
    }

    @PostMapping("/delete/all")
    public String deleteAll(RedirectAttributes redirectAttributes){
        this.repository.deleteAll();

        redirectAttributes.addFlashAttribute("message", "Todos los productos han sido eliminados.");
        redirectAttributes.addFlashAttribute("alert", "success");
        return "redirect:/products";
    }

    /*
GET http://localhost:8080/products
 */
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword,Model model){

        List<Product> products = this.repository.findAllByTitleContaining(keyword);
        model.addAttribute("products", products);
        return "product-search";
    }

}

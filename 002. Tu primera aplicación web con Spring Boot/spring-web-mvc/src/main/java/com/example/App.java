package com.example;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        ProductRepository repository = context.getBean(ProductRepository.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);

        List<Product> products = List.of(
                new Product(null, "Pantalón", 5.99, 1),
                new Product(null, "Sudadera", 6.99, 2),
                new Product(null, "Zapatos", 7.99, 4),
                new Product(null, "Mancuerna", 8.99, 2),
                new Product(null, "Rodillera", 8.99, 2),
                new Product(null, "Balón", 8.99, 2),
                new Product(null, "Zapatillas", 69.99, 20)
        );

        List<Category> categories = List.of(
          new Category("Deporte", "Equipamiento deportivo","https://ladeportecaeducation.com/wp-content/uploads/2020/12/Deporteca_deporte01-1865x2048.png",true, LocalDateTime.now()),
          new Category("Calzado", "Todo para sus pies","https://www.podoactiva.com/wp-content/uploads/imagenes/blog_35.jpg",true, LocalDateTime.now()),
          new Category("Fútbol", "Todo para fútbol",null,true, LocalDateTime.now()),
          new Category("Arco", "Tiro con arco",null,false, LocalDateTime.now())
        );
        categories.get(3).setState(false);

        categoryRepository.saveAll(categories);
        for(Product p: products) {
            p.setCategory(categories.get(0));
        }
        products.get(2).setCategory(categories.get(1));
        products.get(6).setCategory(null);

        repository.saveAll(products);
        /*
        List<Product> products2 = repository.findAllByPrice(8.99);
        products2.forEach(System.out::println);

        List<Product> products3 = repository.findAll();
        System.out.println("Productos con categoria");
        products3.forEach(System.out::println);

        List<Category> categories1 = categoryRepository.findAllByUrlImageIsNull();
        System.out.println("Categoria sin imagen");
        categories1.forEach(System.out::println);
        List<Category> categories2 = categoryRepository.findAllByStateTrue();
        System.out.println("Categorias activas");
        categories2.forEach(System.out::println);
        List<Category> categories3 = categoryRepository.findAllByDescriptionContaining("para");
        System.out.println("Categorias que contienen para");
        categories3.forEach(System.out::println);
        */

    }

}

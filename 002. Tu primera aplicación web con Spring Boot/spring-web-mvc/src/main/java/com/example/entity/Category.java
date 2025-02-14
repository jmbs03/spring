package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true, nullable = false)
    private String name;

    private String description;

    private String urlImage;

    @Column(nullable = false)
    private Boolean state;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "category", orphanRemoval = false)
    private Set<Product> products = new HashSet<>();

    public Category() {

    }

    public Category(String name, String description, String urlImage, Boolean state, LocalDateTime lt) {
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
        this.state = state;
        this.creationDate = lt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", state=" + state +
                ", creationDate=" + creationDate +
                '}';
    }
}

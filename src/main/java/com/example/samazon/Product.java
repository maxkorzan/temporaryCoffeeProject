package com.example.samazon;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String image;
    private double price;
    private boolean onSale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "productsInCart")
    private Set<Cart> carts;


    /////////////////////////////////////////////////////////////

    public Product() {
        this.image="https://res.cloudinary.com/dwsdggfi5/image/upload/v1583268617/defaultcarImage_kijrsj.jpg";
    }

    public Product(String name, String description, String image, double price, boolean onSale, Category category, User user) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.onSale = onSale;
        this.category = category;
        this.user = user;
    }

    /////////////////////////////////////////////////////////////


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

}

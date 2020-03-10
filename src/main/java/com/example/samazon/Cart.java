package com.example.samazon;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean enabled;
    private double sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    private Set<Product> productsInCart;

    ///////////////////////////////////////////////////////
    public Cart() {
        this.enabled = false;
    }

    ///////////////////////////////////////////////////////

//    method to remove item from Set<Product> productsInCart
    public void removeProductFromSet(Product product){
        this.productsInCart.remove(product);     //remove product from productsInCart set
        product.getCarts().remove(this);     //break the relationship from the product side
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(Set<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }

}

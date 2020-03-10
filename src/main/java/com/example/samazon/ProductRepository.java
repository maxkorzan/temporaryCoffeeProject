package com.example.samazon;

import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface ProductRepository extends CrudRepository<Product, Long> {
    public ArrayList<Product> findByNameContainsIgnoreCase(String name);

}

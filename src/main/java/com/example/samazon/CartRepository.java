package com.example.samazon;

import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByEnabled(boolean enabled);
}

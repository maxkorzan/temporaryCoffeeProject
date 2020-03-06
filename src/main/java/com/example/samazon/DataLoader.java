package com.example.samazon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    ///////////////////////////////////////////// END SECURITY /////////////////////////////////////////////
    ///////////////////////////////////////////// END SECURITY /////////////////////////////////////////////

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... strings) throws Exception {

        /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////

        //CREATE USERS
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        User user2 = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        user2.setRoles(Arrays.asList(adminRole));
        userRepository.save(user2);

        ///////////////////////////////////////////// END SECURITY /////////////////////////////////////////////

        //CREATE CATEGORIES + PRODUCTS
        //////////////////////////////////////////////////////////////////////
        //create "category"
        Category category = new Category();
        category.setName("Truck");

        //create "product"
        Product product = new Product();
        product.setName("Ford F-150 Raptor");
        product.setYear("2019");
        product.setPrice("$65,499");
        product.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/f150_ur1kc5.jpg");
        product.setUser(user2);

        //create empty set "products", add "product" object, and add the set to "category"
        Set<Product> products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);

        //define and save category for "product"
        product.setCategory(category);
        productRepository.save(product);

        //////////////////////////////////////////////////////////////////////
        //create multiple products for a single category
        category = new Category();
        category.setName("SUV");

        Product product1 = new Product();
        product1.setName("Land Rover Discovery");
        product1.setYear("2018");
        product1.setPrice("58,399");
        product1.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/landrover_ehtzmc.jpg");
        product1.setUser(user2);

        Product product2 = new Product();
        product2.setName("Porsche Cayenne Turbo S");
        product2.setYear("2019");
        product2.setPrice("$94,899");
        product2.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/cayenne_lerhuo.jpg");
        product2.setUser(user2);

        Product product3 = new Product();
        product3.setName("Mercedes-Benz GLS63 AMG");
        product3.setYear("2019");
        product3.setPrice("$78,299");
        product3.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/gls_amg_oy9ful.jpg");
        product3.setUser(user2);


        products = new HashSet<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        category.setProducts(products);
        categoryRepository.save(category);

        product1.setCategory(category);
        product2.setCategory(category);
        product3.setCategory(category);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);


        //////////////////////////////////////////////////////////////////////
        category = new Category();
        category.setName("Commercial Fleet");
        product = new Product();
        product.setName("Chevrolet Silverado Z71");
        product.setYear("2019");
        product.setPrice("$47,899");
        product.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/silverado_ehheig.jpg");
        product.setUser(user2);

        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);

        product.setCategory(category);
        productRepository.save(product);


        //////////////////////////////////////////////////////////////////////
        category = new Category();
        category.setName("Car");
        product = new Product();
        product.setName("BMW M3");
        product.setYear("2020");
        product.setPrice("$67,999");
        product.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924914/Images/vehicle/m3_vmeqbn.jpg");
        product.setUser(user2);

        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);

        product.setCategory(category);
        productRepository.save(product);


        //////////////////////////////////////////////////////////////////////
        category = new Category();
        category.setName("Motorcycle");
        product = new Product();
        product.setName("Ducati Panigale V4 S");
        product.setYear("2020");
        product.setPrice("$18,699");
        product.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1583350039/Images/vehicle/panigale_dot1ud.jpg");
        product.setUser(user2);

        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);

        product.setCategory(category);
        productRepository.save(product);


        //////////////////////////////////////////////////////////////////////
    }

}

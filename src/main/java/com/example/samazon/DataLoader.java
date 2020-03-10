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

        User user = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        User user2 = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
        user2.setRoles(Arrays.asList(userRole));
        userRepository.save(user2);

        ///////////////////////////////////////////// END SECURITY /////////////////////////////////////////////

        //CREATE CATEGORIES + PRODUCTS
        //////////////////////////////////////////////////////////////////////
        //create "category"
        Category category = new Category();
        category.setName("Coffee Beans");

        //create "product"
        Product product = new Product("Arabica", "tastey beans", "cloudinarylinkhere.com", 20.00, false, category, user);

        //create empty set "products", add "product" object, and add the set to "category"
        Set<Product> products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);

        //define and save category for "product"
        product.setCategory(category);
        productRepository.save(product);


        category = new Category();
        category.setName("Kettle");
        product = new Product("Kettle", "a metal pot", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/metalkettle_l1jew6.jpg", 20.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Kettle");
        product = new Product("Kettle", "a red metal pot", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/redkettle_cnfzrv.png", 25.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Coffee Press");
        product = new Product("french press", "a french press", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861549/frenchpress_gip6lw.jpg", 25.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Coffee Press");
        product = new Product(" chaulk french press", "a chaulk french press", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/chalkfrenchpress_zikr20.jpg", 35.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Espresso Machines");
        product = new Product("espresso machine - large", "an industrial espresso machine", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861549/fancyespresso_fewl8n.jpg", 17035.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Espresso Machines");
        product = new Product("espresso machine - small", "a small espresso machine", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/singleespressomach_nezrps.jpg", 200.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Milk Frother");
        product = new Product("milk frother", "a small electric milk frother", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861549/frother_nftwtw.jpg", 50.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Milk Frother");
        product = new Product("milk frother pitcher", "a small metal frothing pitcher", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/milkfrotherpicture_msldyr.jpg", 25.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Cold Brew");
        product = new Product("small cold brew pitcher", "a small cold brew pitcher", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/Primula-Burke-Cold-Brew-Maker-2-SOURCE-Amazon_zkdbkw.jpg", 25.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Cold Brew");
        product = new Product("large cold brew pitcher", "a large cold brew pitcher", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/coldbrew_m3n4cn.jpg", 225.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Coffee Grinder");
        product = new Product("large coffee grinder", "a large coffee grinder", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/coffeegrinder_yt9l2k.jpg", 500.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);


        category = new Category();
        category.setName("Coffee Grinder");
        product = new Product("small coffee grinder", "a small coffee grinder", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861549/handcoffeegrinder_wj7ewm.jpg", 50.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Coffee Maker");
        product = new Product("small coffee maker", "a small coffee maker", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/mrcoffeebrew_bsmy5y.jpg", 50.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Coffee Maker");
        product = new Product("medium coffee maker", "a medium coffee maker", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/coffeebrew_jcz50w.jpg", 150.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Coffee Cups");
        product = new Product("high tech coffee cup", "a high tech coffee cup with bluetooth", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861549/ember_baxv9v.jpg", 200.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Coffee Cups");
        product = new Product("low tech coffee cup", "a low tech coffee cup with spoon", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861548/coffeemugsspoon_y0gez1.jpg", 20.00, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

//        //////////////////////////////////////////////////////////////////////
//        //create multiple products for a single category
//        category = new Category();
//        category.setName("SUV");
//
//        Product product1 = new Product();
//        product1.setName("Land Rover Discovery");
//        product1.setYear("2018");
//        product1.setPrice("58,399");
//        product1.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/landrover_ehtzmc.jpg");
//        product1.setUser(user2);
//
//        Product product2 = new Product();
//        product2.setName("Porsche Cayenne Turbo S");
//        product2.setYear("2019");
//        product2.setPrice("$94,899");
//        product2.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/cayenne_lerhuo.jpg");
//        product2.setUser(user2);
//
//        Product product3 = new Product();
//        product3.setName("Mercedes-Benz GLS63 AMG");
//        product3.setYear("2019");
//        product3.setPrice("$78,299");
//        product3.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/gls_amg_oy9ful.jpg");
//        product3.setUser(user2);
//
//
//        products = new HashSet<>();
//        products.add(product1);
//        products.add(product2);
//        products.add(product3);
//
//        category.setProducts(products);
//        categoryRepository.save(category);
//
//        product1.setCategory(category);
//        product2.setCategory(category);
//        product3.setCategory(category);
//        productRepository.save(product1);
//        productRepository.save(product2);
//        productRepository.save(product3);
//
//
//        //////////////////////////////////////////////////////////////////////
//        category = new Category();
//        category.setName("Commercial Fleet");
//        product = new Product();
//        product.setName("Chevrolet Silverado Z71");
//        product.setYear("2019");
//        product.setPrice("$47,899");
//        product.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/silverado_ehheig.jpg");
//        product.setUser(user2);
//
//        products = new HashSet<>();
//        products.add(product);
//        category.setProducts(products);
//        categoryRepository.save(category);
//
//        product.setCategory(category);
//        productRepository.save(product);
//
//
//        //////////////////////////////////////////////////////////////////////
//        category = new Category();
//        category.setName("Car");
//        product = new Product();
//        product.setName("BMW M3");
//        product.setYear("2020");
//        product.setPrice("$67,999");
//        product.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924914/Images/vehicle/m3_vmeqbn.jpg");
//        product.setUser(user2);
//
//        products = new HashSet<>();
//        products.add(product);
//        category.setProducts(products);
//        categoryRepository.save(category);
//
//        product.setCategory(category);
//        productRepository.save(product);
//
//
//        //////////////////////////////////////////////////////////////////////
//        category = new Category();
//        category.setName("Motorcycle");
//        product = new Product();
//        product.setName("Ducati Panigale V4 S");
//        product.setYear("2020");
//        product.setPrice("$18,699");
//        product.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1583350039/Images/vehicle/panigale_dot1ud.jpg");
//        product.setUser(user2);
//
//        products = new HashSet<>();
//        products.add(product);
//        category.setProducts(products);
//        categoryRepository.save(category);
//
//        product.setCategory(category);
//        productRepository.save(product);
//
//
//        //////////////////////////////////////////////////////////////////////
    }

}

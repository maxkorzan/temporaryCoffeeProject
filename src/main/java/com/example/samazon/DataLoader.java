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
//        Category category = new Category();
//        Product product = new Product();
//        Set<Product> products = new HashSet<>();


        //        ESPRESSO BEAN---------------------------------------------
        Category category = new Category();
        category.setName("Espresso Bean");
        Product product = new Product("Italian Roast Espresso 1lb", "This signature blend of Italian roasted coffee beans from South America and India is a heavy, full-bodied roast. Displaying a toasty, honeyed aroma and notes of cocoa powder and smoky molasses, these gourmet beans are our most popular coffee blend.", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861619/CoffeeBag_slfejy.jpg", 7.99, false, category, user);
        Set<Product> products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Espresso Bean");
        product = new Product("Italian Roast Espresso 5lb", "This signature blend of Italian roasted coffee beans from South America and India is a heavy, full-bodied roast. Displaying a toasty, honeyed aroma and notes of cocoa powder and smoky molasses, these gourmet beans are our most popular coffee blend.", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861619/CoffeeBag_slfejy.jpg", 29.99, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Espresso Bean");
        product = new Product("Arabian Mocha Sanani 1lb", "Harsh, dry mountain terrain and primitive farming give this full-bodied desert miracle a distinctive wildness. Lush and full bodied with hints of cocoa and dates, and a complex, pungent aroma.", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861619/CoffeeBag_slfejy.jpg", 7.99, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();
        category.setName("Espresso Bean");
        product = new Product("Arabian Mocha Sanani 5lb", "Harsh, dry mountain terrain and primitive farming give this full-bodied desert miracle a distinctive wildness. Lush and full bodied with hints of cocoa and dates, and a complex, pungent aroma.", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861619/CoffeeBag_slfejy.jpg", 27.99, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        //        COFFEE BEAN----------------------------------------------

        category = new Category();
        category.setName("Coffee Bean");
        product = new Product("Breakfast Blend 1lb", "Our Master Roaster created this blend specifically for your perfect breakfast cup of coffee. It’s just the “jolt” you need to get going in the morning! This is a medium-bodied blend of light and dark beans with a hint of acidity and tartness.", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861619/CoffeeBag_slfejy.jpg", 6.99, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();

        product = new Product("Breakfast Blend 5lb", "Our Master Roaster created this blend specifically for your perfect breakfast cup of coffee. It’s just the “jolt” you need to get going in the morning! This is a medium-bodied blend of light and dark beans with a hint of acidity and tartness.", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861619/CoffeeBag_slfejy.jpg", 27.99, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        category = new Category();

        product = new Product("Dark Sumatra Mandheling 1lb", "A staple of coffee shops throughout the world, Sumatra has a rich, earthy flavor and is considered to be among the finest beans available. ", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861619/CoffeeBag_slfejy.jpg", 7.99, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        product = new Product("Dark Sumatra Mandheling 5lb", "A staple of coffee shops throughout the world, Sumatra has a rich, earthy flavor and is considered to be among the finest beans available.", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861619/CoffeeBag_slfejy.jpg", 26.99, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        product = new Product("Ethiopian Yirgacheffe 1lb", "This single-origin Ethiopian coffee offers a smooth yet surprisingly deep body and crisp acidity. Displaying a sweet, floral aroma and flavor notes reminiscent of citrus and wine.", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861619/CoffeeBag_slfejy.jpg", 7.99, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

        product = new Product("Ethiopian Yirgacheffe 5lb", "This single-origin Ethiopian coffee offers a smooth yet surprisingly deep body and crisp acidity. Displaying a sweet, floral aroma and flavor notes reminiscent of citrus and wine.", "https://res.cloudinary.com/dblb2mhw4/image/upload/v1583861619/CoffeeBag_slfejy.jpg", 27.99, false, category, user);
        products = new HashSet<>();
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);

//        END OF BEANS------------------------------------------


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


    }

}
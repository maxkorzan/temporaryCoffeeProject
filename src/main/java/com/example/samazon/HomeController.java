package com.example.samazon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.utils.ObjectUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CloudinaryConfig cloudc;
    @Autowired
    private UserService userService;
    @Autowired
    CartRepository cartRepository;


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////// SECURITY ////////////////////////////////////////////////
    //////////////////////////////////////////////// SECURITY ////////////////////////////////////////////////

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    public CartRepository getCartRepository() {
        return cartRepository;
    }

    //USER REGISTRATION
    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        if (result.hasErrors()){
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "login";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //MY ACCOUNT
    @RequestMapping("/secure")
    public String secure(Principal principal, Model model){
        String username = principal.getName();  /* Principal.getName <-- this gets you the current user's "username" */
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    ////////////////////////////////////////////// END SECURITY //////////////////////////////////////////////
    ////////////////////////////////////////////// END SECURITY //////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////



    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //HOME PAGE - index
    @RequestMapping("/")
    public String index(Model model, Principal principal, Authentication authentication){
        //pull all categories from repo --> template
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("users", userRepository.findAll());

        //check for currently logged in "user", if no current user then set to "0" to prevent errors
        String username = null;
        try {
            username = principal.getName();
            model.addAttribute("product_user_id", userRepository.findByUsername(principal.getName()).getId());
            model.addAttribute("user_id", userRepository.findByUsername(principal.getName()).getId());

            return "index";
        } catch (Exception e){
            model.addAttribute("product_user_id", 0);
            return "index";
        }

    }

    //CATEGORY PAGE - Duplicate of Index, but filters results by selected "category"
    @RequestMapping("/category/{id}")
    public String category(@PathVariable("id") long id, Model model, Principal principal, Authentication authentication) {
        model.addAttribute("category", categoryRepository.findById(id).get());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
//        model.addAttribute("product_user_id", userRepository.findByUsername(principal.getName()).getId());

        //check for currently logged in "user", if no current user then set to "0" to prevent errors
        String username = null;
        try {
            username = principal.getName();
            model.addAttribute("product_user_id", userRepository.findByUsername(principal.getName()).getId());
            return "category";
        } catch (Exception e){
            model.addAttribute("product_user_id", 0);
            return "category";
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //SEARCH
    @RequestMapping("/search")
    public String search(@RequestParam("search") String searchTerm, Model model, Principal principal, Authentication authentication){
        model.addAttribute("productSearch", productRepository.findByNameContainsIgnoreCase(searchTerm));

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("users", userRepository.findAll());

        String username = null;
        try {
            username = principal.getName();
            model.addAttribute("product_user_id", userRepository.findByUsername(principal.getName()).getId());
            return "searchlist";
//            return "redirect:/searchlist";
        } catch (Exception e){
            model.addAttribute("product_user_id", 0);
            return "searchlist";
//            return "redirect:/searchlist";

        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ADD CATEGORY
    @GetMapping("/addCategory")
    public String formCategory(Model model){
        model.addAttribute("category", new Category());
        return "formCategory";
    }

    @PostMapping("/processCategory")
    public String processForm(@Valid Category category, BindingResult result){
        if (result.hasErrors()){
            return "formCategory";
        }
        categoryRepository.save(category);
        return "redirect:/addProduct";
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ADD PRODUCT
    @GetMapping("/addProduct")
    public String formProduct(Model model, Principal principal){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("user_id",userRepository.findByUsername(principal.getName()).getId());
        return "formProduct";
    }

    @PostMapping("/processProduct")
    public String processForm(@Valid @ModelAttribute Product product, BindingResult result, /*@RequestParam("pic") String pic,*/
                              @RequestParam("category") long id, @RequestParam("file") MultipartFile file,
                              @RequestParam("product_user_id") long product_user_id, Principal principal){
        if (result.hasErrors()){
            return "formProduct";
        }

        //upload image to Cloudinary
        if (file.isEmpty()){
            /*do nothing*/
        }
        else {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                product.setImage(uploadResult.get("url").toString());
                productRepository.save(product);
            } catch (IOException e){
                e.printStackTrace();
                return "redirect:/addProduct";
            }
        }

        User user = userRepository.findById(product_user_id).get();
        product.setUser(user);

        Category category = categoryRepository.findById(id).get();
        product.setCategory(category);
        productRepository.save(product);
        return "redirect:/";

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DETAIL, UPDATE, DELETE
    @RequestMapping("/detail/{id}")
    public String showproduct(@PathVariable("id") long id, Model model, Principal principal, Authentication authentication) {
        model.addAttribute("product", productRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
//        model.addAttribute("product_user_id", userRepository.findByUsername(principal.getName()).getId());

        String username = null;
        try {
            username = principal.getName();
            model.addAttribute("product_user_id", userRepository.findByUsername(principal.getName()).getId());
            return "show";
        } catch (Exception e){
            model.addAttribute("product_user_id", 0);
            return "show";
        }
    }

    @RequestMapping("/update/{id}")
    public String updateproduct(@PathVariable("id") long id, Model model, Principal principal) {
        model.addAttribute("product", productRepository.findById(id).get());
        model.addAttribute("user_id", userRepository.findByUsername(principal.getName()).getId());

        Product product = productRepository.findById(id).get();
//        String pic=product.getImage();                //THE HARD WAY USING REQUESTPARAM
//        model.addAttribute("pic",pic);                //THE HARD WAY USING REQUESTPARAM
        model.addAttribute("product", product);

        model.addAttribute("categories", categoryRepository.findAll());
        return "formProduct";
    }

    @RequestMapping("/delete/{id}")
    public String delproduct(@PathVariable("id") long id, Model model) {
        productRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/removeProduct/{id}")
    public String removeProduct(@PathVariable("id") long id, Model model) {
        model.addAttribute("carts", cartRepository.findAll());
        model.addAttribute("product", productRepository.findById(id).get());

//        Cart currentCart = cartRepository.findById(id);
//        currentCart.removeProductFromSet(product);

        return "redirect:/cart";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //CART PAGE
//    @RequestMapping("/cart/{user_id}")
//    public String cart(@PathVariable("user_id") long id, Model model, Principal principal){
//        model.addAttribute("products", productRepository.findAll());
//        model.addAttribute("carts", cartRepository.findAll());
//
////      model.addAttribute("product", new Product());
//        model.addAttribute("product", productRepository.findById(id).get());
//
//        model.addAttribute("product_user_id", userRepository.findByUsername(principal.getName()).getId());
//    }
//    }

    //CART PAGE -- SIMPLIFIED VERSION
    @RequestMapping("/cart")
    /*@RequestMapping("/cart/{cart_id}")*/
    public String cart(Model model, Principal principal, Authentication authentication /*@PathVariable("cart_id") long id*/) {
        double sum = 0;
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("carts", cartRepository.findAll());

        if(cartRepository.findByEnabled(true) != null){
            Cart currentCart = cartRepository.findByEnabled(true);
            Set<Product> productsInCart = currentCart.getProductsInCart();

            for(Product product : productsInCart){
                sum += product.getPrice();
                System.out.println("(if) sum = " + sum);
            }

            currentCart.setSum(sum);
            cartRepository.save(currentCart);
        }
        else {
            Cart currentCart = new Cart();
            currentCart.setEnabled(true); //sets this cart as "active"

            Set<Product> productsInCart = new HashSet<>();
            currentCart.setProductsInCart(productsInCart);
            cartRepository.save(currentCart);
        }

        //check for currently logged in "user", if no current user then set to "0" to prevent errors
        String username = null;
        try {
            username = principal.getName();
            model.addAttribute("product_user_id", userRepository.findByUsername(principal.getName()).getId());
            model.addAttribute("user_id", userRepository.findByUsername(principal.getName()).getId());
            return "cart";
        } catch (Exception e) {
            model.addAttribute("product_user_id", 0);
            return "cart";
        }

    }


    //ADD PRODUCT TO CART
    @RequestMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") long id, Model model, Principal principal, Authentication authentication){
        model.addAttribute("product", productRepository.findById(id).get());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("carts", cartRepository.findAll());
        model.addAttribute("user_id", userRepository.findByUsername(principal.getName()).getId());



        if(cartRepository.findByEnabled(true) != null){
            Cart currentCart = cartRepository.findByEnabled(true);
//            Set<Product> productsInCart = new HashSet<>();
            Set<Product> productsInCart = currentCart.getProductsInCart();
            productsInCart.add(productRepository.findById(id).get());
            currentCart.setProductsInCart(productsInCart);

            cartRepository.save(currentCart);
        }
        else {
            Cart currentCart = new Cart();
            currentCart.setEnabled(true); //sets this cart as "active"

            Set<Product> productsInCart = new HashSet<>();
            productsInCart.add(productRepository.findById(id).get());
            currentCart.setProductsInCart(productsInCart);

            cartRepository.save(currentCart);
        }

        return "redirect:/";

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //PAST ORDERS
    @RequestMapping("/pastOrders")
    public String pastOrders(){
        return "pastOrders";
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //CHECKOUT
    @RequestMapping("/checkout")
    public String checkout(){
        return "checkout";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //PAYMENT AND ADDRESS FORM PAGE
    @RequestMapping("/formPayment")
    public String formPayment(){
        return "formPayment";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //FINAL CONFIRMATION PAGE BEFORE PLACING ORDER
    @RequestMapping("/finalConfirm")
    public String finalConfirm(){
        return "finalConfirm";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //PLACE ORDER
    @RequestMapping("/placeOrder")
    public String placeOrder(){
        return "placeOrder";
    }
    //send email

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //THANK YOU, ORDER PLACED
    //currentCart.setEnabled = "false";         //move this order from "active" to "past order"
    //"back to Home" button

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //Credit form

    @RequestMapping("/credit")
    public String creditform(){
        return "creditform";
    }


} //end HomeController

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
import java.util.Map;

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


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////// SECURITY ////////////////////////////////////////////////
    //////////////////////////////////////////////// SECURITY ////////////////////////////////////////////////

    @RequestMapping("/login")
    public String login(){
        return "login";
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
        return "index";
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

        //check for currently logged in "user", if no current user then set to "0" to prevent errors
        String username = null;
        try {
            username = principal.getName();
            model.addAttribute("product_user_id", userRepository.findByUsername(principal.getName()).getId());
            return "index";
        } catch (Exception e){
            model.addAttribute("product_user_id", 0);
            return "index";
        }

    }

    //CATEGORY - Duplicate of Index, but filter results by selected "category"
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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

} //end HomeController

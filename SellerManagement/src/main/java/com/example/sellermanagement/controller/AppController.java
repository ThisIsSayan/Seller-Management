package com.example.sellermanagement.controller;

import com.example.sellermanagement.entity.*;
import com.example.sellermanagement.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins  =  "*")
public class AppController {

    @Autowired
    private AppService appService;

    private String userName = null;

    @GetMapping("/login")
    public Map<String,String> login() {
        getUsername();
        return appService.getStatusService(userName);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.isActive());
        user.setDob(userDTO.getDob());
        user.setRegistration(userDTO.getRegistration());
        user.setStatus(userDTO.getStatus());
        user.setRoles(userDTO.getRoles());
        user.setName(userDTO.getName());
        return appService.signupService(user);
    }

    public Map<String,String> getStatus() {
        return appService.getStatusService(this.userName);
    }

    public void getUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = null;
        if (authentication != null) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        this.userName = username;
    }

    @PostMapping("/addproduct")
    public void addProduct(@RequestBody ProductEntry productEntry) {

        Product product = new Product();
        product.setProductName(productEntry.getProductName());
        product.setPrice(productEntry.getPrice());
        product.setUserName(this.userName);
        product.setProductImage(productEntry.getProductImage());
        product.setUserName(this.userName);
        product.setCategory(productEntry.getCategory());
        product.setQuantity(productEntry.getQuantity());
        product.setDescription(productEntry.getDescription());
        appService.addProductService(product);
    }

    @GetMapping("/getproducts")
    public List<Product> getProducts() {
        return appService.getProductsService(this.userName);
    }

    @GetMapping("/getallproducts")
    public List<Product> getAllProducts() {
        return appService.getAllProductsService();
    }

    @GetMapping("/deleteproduct")
    public void deleteProduct(@RequestParam int id) {
        appService.deleteProductService(id, this.userName);
    }

    @PostMapping("/editproduct")
    public void updateProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();

        product.setId(productDTO.getId());
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setUserName(productDTO.getUserName());
        product.setProductImage(productDTO.getProductImage());
        product.setCategory(productDTO.getCategory());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        appService.editProductService(product, this.userName);
    }


    @PostMapping("/setuserstatus")
    public void setuserStatus(@RequestParam int id, String status) {
        appService.setUserStatusService(id, status);
    }

    @GetMapping("/getallusers")
    public List<GetUsers> getAllUsers() {
        return appService.getAllUsersService();

    }

    @GetMapping("/totalitemsno")
    public int totalItemsNo() {
        return appService.totalItemsNoService();
    }

    @GetMapping("/totalsellersno")
    public int totalSellersNo() {
        return appService.totalSellersNoService();
    }

    @GetMapping("/pendingsellers")
    public List<GetUsers> pendingSellers() {
        return appService.pendingSellersService();
    }

    @GetMapping("/approvedsellers")
    public List<GetUsers> approvedSellers() {
        return appService.approvedSellersService();
    }

    @GetMapping("/deniedsellers")
    public List<GetUsers> deniedSellers() {
        return appService.deniedSellersService();
    }

    @GetMapping("/getcurrentuser")
    public CurrentUser getCurrentUser() {
        return appService.getCurrentUserService(this.userName);
    }

    @PostMapping("/addadmin")
    public String addAdmin(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.isActive());
        user.setDob(userDTO.getDob());
        user.setRegistration(userDTO.getRegistration());
        user.setStatus(userDTO.getStatus());
        user.setRoles(userDTO.getRoles());
        user.setName(userDTO.getName());
        return appService.addAdminService(user);
    }

    @GetMapping("/deleteaccount")
    public String deleteAccount() {
        return appService.deleteAccountService(this.userName);
    }

}

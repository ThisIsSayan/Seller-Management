package com.example.sellermanagement.serviceimpl;

import com.example.sellermanagement.entity.CurrentUser;
import com.example.sellermanagement.entity.GetUsers;
import com.example.sellermanagement.entity.Product;
import com.example.sellermanagement.entity.User;
import com.example.sellermanagement.repo.ProductRepo;
import com.example.sellermanagement.repo.UserRepo;
import com.example.sellermanagement.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    private static final String SELLER_ROLE = "ROLE_USER";

    @Override
    public String signupService(User user) {
        if (!userRepo.findByUserName(user.getUserName()).isPresent())
        {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encPass = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encPass);
            userRepo.save(user);
            return "Signup Successfull";
        }
        return "User already exists";
    }

    @Override
    public String getRegistration(String userName) {
        return userRepo.findByUserName(userName).orElseThrow().getRegistration();
    }

    @Override
    public void addProductService(Product product) {
        productRepo.save(product);
    }

    @Override
    public List<Product> getProductsService(String userName) {
        return productRepo.findByUserName(userName);
    }

    @Override
    public List<Product> getAllProductsService() {
        return productRepo.findAll();
    }

    @Override
    public void deleteProductService(int id, String userName) {
        if(productRepo.findById(id).orElseThrow().getUserName().equals(userName) || userRepo.findByUserName(userName).orElseThrow().getRoles().contains("ROLE_ADMIN"))
        {
            productRepo.deleteById(id);
        }
    }

    @Override
    public void editProductService(Product product, String userName) {
        Product temp = new Product();
        temp.setId(product.getId());
        temp.setProductName(product.getProductName());
        temp.setPrice(product.getPrice());
        temp.setUserName(userName);
        temp.setProductImage(product.getProductImage());
        temp.setCategory(product.getCategory());
        temp.setQuantity(product.getQuantity());
        temp.setDescription(product.getDescription());
        productRepo.save(temp);
    }

    @Override
    public void setUserStatusService(int id, String status) {
        Optional<User> getUser = userRepo.findById(id);
        User user = new User();
        user.setId(id);
        user.setUserName(getUser.orElseThrow().getUserName());
        user.setPassword(getUser.orElseThrow().getPassword());
        if(status.equals("DENIED"))
            user.setActive(false);
        else
            user.setActive(getUser.orElseThrow().isActive());
        user.setDob(getUser.orElseThrow().getDob());
        user.setRegistration(getUser.orElseThrow().getRegistration());
        user.setStatus(status);
        user.setRoles(getUser.orElseThrow().getRoles());
        user.setName(getUser.orElseThrow().getName());
        userRepo.save(user);
    }

    @Override
    public List<GetUsers> getAllUsersService() {
        List<User> users = userRepo.findAll();
        List<GetUsers> getUsers = new ArrayList<>();
        for(User user : users)
        {
            if(user.getRoles()!=null && user.getRoles().equals(SELLER_ROLE))
            {
                GetUsers temp = new GetUsers();
                temp.setId(user.getId());
                temp.setDob(user.getDob());
                temp.setUserName(user.getUserName());
                temp.setRegistration(user.getRegistration());
                temp.setStatus(user.getStatus());
                temp.setName(user.getName());
                getUsers.add(temp);
            }
        }
        return getUsers;
    }

    @Override
    public int totalItemsNoService() {
        List<Product> products = productRepo.findAll();
        int count = 0;
        for(Product product : products)
        {
        	count += product.getQuantity();
        }
        return count;
    }

    @Override
    public int totalSellersNoService() {
        List<User> users = userRepo.findAll();
        int count = 0;
        for(User user : users)
        {
            if(user.getRoles()!=null && user.getRoles().equals(SELLER_ROLE))
            {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<GetUsers> pendingSellersService() {
        List<User> users = userRepo.findAll();
        List<GetUsers> getUsers = new ArrayList<>();
        for(User user : users)
        {
            if(user.getRoles()!=null && user.getRoles().equals(SELLER_ROLE) && user.getStatus().equals("PENDING"))
            {
                GetUsers temp = new GetUsers();
                temp.setId(user.getId());
                temp.setDob(user.getDob());
                temp.setUserName(user.getUserName());
                temp.setRegistration(user.getRegistration());
                temp.setStatus(user.getStatus());
                temp.setName(user.getName());
                getUsers.add(temp);
            }
        }
        return getUsers;
    }

    @Override
    public List<GetUsers> approvedSellersService() {
        List<User> users = userRepo.findAll();
        List<GetUsers> getUsers = new ArrayList<>();
        for(User user : users)
        {
            if(user.getRoles()!=null && user.getRoles().equals(SELLER_ROLE) && user.getStatus().equals("APPROVED"))
            {
                GetUsers temp = new GetUsers();
                temp.setId(user.getId());
                temp.setDob(user.getDob());
                temp.setUserName(user.getUserName());
                temp.setRegistration(user.getRegistration());
                temp.setStatus(user.getStatus());
                temp.setName(user.getName());
                getUsers.add(temp);
            }
        }
        return getUsers;
    }

    @Override
    public List<GetUsers> deniedSellersService() {
        List<User> users = userRepo.findAll();
        List<GetUsers> getUsers = new ArrayList<>();
        for(User user : users)
        {
            if(user.getRoles()!=null && user.getRoles().equals(SELLER_ROLE) && user.getStatus().equals("DENIED"))
            {
                GetUsers temp = new GetUsers();
                temp.setId(user.getId());
                temp.setDob(user.getDob());
                temp.setUserName(user.getUserName());
                temp.setRegistration(user.getRegistration());
                temp.setStatus(user.getStatus());
                temp.setName(user.getName());
                getUsers.add(temp);
            }
        }
        return getUsers;
    }

    @Override
    public CurrentUser getCurrentUserService(String userName) {
        Optional<User> currentUser = userRepo.findByUserName(userName);
        CurrentUser temp = new CurrentUser();
        temp.setDob(currentUser.orElseThrow().getDob());
        temp.setRegistration(currentUser.orElseThrow().getRegistration());
        temp.setUserName(currentUser.orElseThrow().getUserName());
        temp.setStatus(currentUser.orElseThrow().getStatus());
        temp.setName(currentUser.orElseThrow().getName());
        return temp;
    }

    @Override
    public String addAdminService(User user) {
        User temp = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encPass = bCryptPasswordEncoder.encode(user.getPassword());
        temp.setName(user.getName());
        temp.setPassword(encPass);
        temp.setActive(user.isActive());
        temp.setRoles(user.getRoles());
        temp.setDob(user.getDob());
        temp.setId(user.getId());
        temp.setRegistration(user.getRegistration());
        temp.setUserName(user.getUserName());
        temp.setStatus(user.getStatus());
        userRepo.save(temp);

        return "Admin added sucessfully";
    }

    @Override
    public Map<String,String> getStatusService(String userName) {
    	String role = userRepo.findByUserName(userName).orElseThrow().getRoles();
        String status = userRepo.findByUserName(userName).orElseThrow().getStatus();
        Map<String,String> result = new HashMap<>();
        result.put("Role", role);
        result.put("Status", status);
        return result;
    }

    @Override
    public String deleteAccountService(String username) {
        Optional<User> getUser = userRepo.findByUserName(username);
        User user = new User();
        user.setId(getUser.orElseThrow().getId());
        user.setUserName(getUser.orElseThrow().getUserName());
        user.setPassword(getUser.orElseThrow().getPassword());
        user.setActive(false);
        user.setDob(getUser.orElseThrow().getDob());
        user.setRegistration(getUser.orElseThrow().getRegistration());
        user.setStatus("DELETED");
        user.setRoles(getUser.orElseThrow().getRoles());
        user.setName(getUser.orElseThrow().getName());
        userRepo.save(user);
        List<Product> productList = productRepo.findByUserName(username);
        for(Product p : productList)
        {
            productRepo.deleteById(p.getId());
        }
        return "Sucessfully deleted";
    }

}

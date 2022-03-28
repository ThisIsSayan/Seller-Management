package com.example.sellermanagement.service;

import com.example.sellermanagement.entity.CurrentUser;
import com.example.sellermanagement.entity.GetUsers;
import com.example.sellermanagement.entity.Product;
import com.example.sellermanagement.entity.User;

import java.util.List;
import java.util.Map;


public interface AppService {
    public String signupService(User user);

    public String getRegistration(String userName);

    public void addProductService(Product product);

    public List<Product> getProductsService(String userName);

    public List<Product> getAllProductsService();

    public void deleteProductService(int id, String userName);

    public void editProductService(Product product, String userName);

    public void setUserStatusService(int id, String status);

    public List<GetUsers> getAllUsersService();

    public int totalItemsNoService();

    public int totalSellersNoService();

    public List<GetUsers> pendingSellersService();

    public List<GetUsers> approvedSellersService();

    public List<GetUsers> deniedSellersService();

    public CurrentUser getCurrentUserService(String userName);

    public String addAdminService(User user);

    public Map<String,String> getStatusService(String userName);

    public String deleteAccountService(String userName);
}

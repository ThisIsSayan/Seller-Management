package com.example.sellermanagement.repo;

import com.example.sellermanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    List<Product> findByUserName(String userName);
}

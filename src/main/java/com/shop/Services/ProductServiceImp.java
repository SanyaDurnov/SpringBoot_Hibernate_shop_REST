package com.shop.Services;

import com.shop.Entities.Order;
import com.shop.Entities.Product;
import com.shop.Exceptions.NoEntityException;
import com.shop.Repos.OrderRepository;
import com.shop.Repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;
    private static List<Product> products;
    private static List<Map<String,String>> productsMap = new ArrayList<Map<String,String>>();

    public void addProduct(String productName, Double productPrice){
            productRepository.save(new Product(productName, productPrice));
    }

    public Product findById(long id) throws NoEntityException {
        Product product = productRepository.findById(id).orElseThrow(()
                -> new NoEntityException(id, "Product not found"));
        return product;
    }

    public long findIdByName(String productName){
        return productRepository.findByProductName(productName).getId();
    }


}

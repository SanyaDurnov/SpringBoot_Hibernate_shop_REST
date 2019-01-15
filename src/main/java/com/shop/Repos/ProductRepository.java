package com.shop.Repos;

import com.shop.Entities.Order;
import com.shop.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {

    Product findByProductName(String productName);
}

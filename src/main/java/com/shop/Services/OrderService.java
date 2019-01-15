package com.shop.Services;

import com.shop.Exceptions.NoEntityException;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Map<String,String>> getAll();
    List<Map<String,String>> findByOrderName(String orderName);
    String addOrder(String orderName, Integer quantity, Integer product_id);
    void updateOrder(Long orderId, long newProduct_Id, Integer newQuantity) throws NoEntityException;
    void delOrder(Integer orderId);
    List<Map<String,String>> findFiveMostExpensive();
}

package com.shop.Services;

import com.shop.Entities.Order;
import com.shop.Exceptions.NoEntityException;
import com.shop.Repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductServiceImp productServiceImp;
    @Autowired
    EntityManager entityManager;
    private static List<Order> orders;
    private List<Map<String,String>> ordersMap = new ArrayList<Map<String,String>>();

    public List<Map<String,String>> getAll() {
        ordersMap.clear();
        toMap((List<Order>) orderRepository.findAll());
        return ordersMap;
    }

    public List<Map<String,String>> findByOrderName(String orderName) {
        ordersMap.clear();
            toMap(orderRepository.findByOrderName(orderName));
        return ordersMap;
    }

    public String addOrder(String orderName, Integer quantity, Integer product_id){
        try {
            orderRepository.save(new Order(productServiceImp.findById(product_id), quantity, orderName));
            Iterable<Order> allOrders = orderRepository.findAll();
        }catch (NoEntityException e){
            return "Product id - not found!";
        }
        return "Ok";
    }

    private void toMap(List<Order> orders){
        for (Order item: orders) {
            ordersMap.add(new HashMap<String, String>() {{
                put("orderName",item.getOrderName());
                put("productId",item.getProduct().toString());
                put("quantity", String.valueOf(item.getQuantity()));
            }});
        }
    }

    public void updateOrder(Long orderId, long newProduct_Id, Integer newQuantity) throws NoEntityException {
        Order order = orderRepository.findById(Math.toIntExact(orderId)).orElseThrow(() ->
                new NoEntityException(orderId,"Order not found"));
        order.setProduct(productServiceImp.findById(newProduct_Id));
        order.setQuantity(newQuantity);
        orderRepository.save(order);
    }

    public void delOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Map<String,String>> findFiveMostExpensive(){
        ordersMap.clear();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = cb.createQuery(Order.class);
        Root<Order> c = criteria.from(Order.class);
        criteria.select(c);
        criteria.orderBy(cb.desc(c.get("Sum")));
        Query query = entityManager.createQuery(criteria);
        query.setMaxResults(5);
        toMap( query.getResultList());
        return ordersMap;
    }
}

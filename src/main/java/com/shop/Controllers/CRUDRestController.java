package com.shop.Controllers;


import com.shop.Exceptions.NoEntityException;
import com.shop.Services.OrderServiceImp;
import com.shop.Services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("crud")
public class CRUDRestController {

    @Autowired
    private OrderServiceImp orderServiceImp;
    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping
    private List<Map<String,String>>get(){
        return orderServiceImp.getAll();
    }

    @GetMapping("get/{orderName}")
    private List<Map<String,String>>getByName(@PathVariable String orderName){
        return orderServiceImp.findByOrderName(orderName);
    }

    @PostMapping("/addProduct/{productName}-{price}")
    private void addProduct(@PathVariable String productName, @PathVariable Double price){
        productServiceImp.addProduct(productName,price);
    }

    @GetMapping("/addOrder/{orderName}-{quantity}-{product_id}")
    private String addOrder(@PathVariable String orderName,@PathVariable Integer quantity,
                            @PathVariable Integer product_id){
       return orderServiceImp.addOrder(orderName,quantity,product_id);
    }

    @GetMapping("/updtOrder/{orderId}-{productId}-{quantity}")
    private void updtOrder(@PathVariable Long orderId, @PathVariable Long productId,
                           @PathVariable Integer quantity) throws NoEntityException {
        orderServiceImp.updateOrder(orderId,productId,quantity);
    }

    @GetMapping("/delOrder")
    private void delOrder(Integer orderId) throws NoEntityException {
        orderServiceImp.delOrder(orderId);
    }

    @GetMapping("get5")
    private List<Map<String,String>>getFiveExpensive(){
        return orderServiceImp.findFiveMostExpensive();
    }

    @GetMapping("putCustomOrder")
    private void putCustomOrder(){
        productServiceImp.addProduct("Pikachu", 201.00);
        productServiceImp.addProduct("Bulbazavr", 30.00);
        productServiceImp.addProduct("Erbok", 23.00);
        productServiceImp.addProduct("Skvirtl", 27.00);
        productServiceImp.addProduct("Pidji", 29.00);
        productServiceImp.addProduct("Nidoran", 21.00);
        productServiceImp.addProduct("Chermeleon", 107.00);
        productServiceImp.addProduct("Spirow", 19.00);
        productServiceImp.addProduct("Katerpi", 11.00);
        orderServiceImp.addOrder("A100",2,289);
        orderServiceImp.addOrder("A200",5,(int)productServiceImp.findIdByName("Chermeleon"));
        orderServiceImp.addOrder("A201",1,(int)productServiceImp.findIdByName("Pikachu"));
        orderServiceImp.addOrder("A202",9,(int)productServiceImp.findIdByName("Pidji"));
        orderServiceImp.addOrder("A203",10,(int)productServiceImp.findIdByName("Erbok"));
        orderServiceImp.addOrder("A204",89,(int)productServiceImp.findIdByName("Katerpi"));
        orderServiceImp.addOrder("A205",15,(int)productServiceImp.findIdByName("Skvirtl"));
        orderServiceImp.addOrder("A206",4,(int)productServiceImp.findIdByName("Nidoran"));
    }


}

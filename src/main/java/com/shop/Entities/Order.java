package com.shop.Entities;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private int id;
    private Integer quantity;
    private String orderName;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Product product;
    private Double Sum;

    public Order() {
    }

    public Order(Integer quantity) {
        this.quantity = quantity;
    }

    public Order(Integer quantity, String orderName, Product product) {
        this.quantity = quantity;
        this.orderName = orderName;
        this.product = product;
    }

    public Order(Product product, int quantity, String orderName) {
        this.product = product;
        this.quantity = quantity;
        this.orderName = orderName;
        this.Sum = this.quantity*this.product.getPrice();
    }

    public Order(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSum() {
        return Sum;
    }

}
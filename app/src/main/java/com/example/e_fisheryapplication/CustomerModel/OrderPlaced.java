package com.example.e_fisheryapplication.CustomerModel;

public class OrderPlaced {
    String category,item;
    int qty,total;

    public OrderPlaced() {
    }

    public OrderPlaced(String category, String item, int qty, int total) {
        this.category = category;
        this.item = item;
        this.qty = qty;
        this.total = total;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

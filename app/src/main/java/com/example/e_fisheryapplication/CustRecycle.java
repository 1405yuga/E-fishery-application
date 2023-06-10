package com.example.e_fisheryapplication;

public class CustRecycle {
    String option,item;
    int avail,price,image;
    int kg;

    public void CustRecycle(){

    }

    public CustRecycle(String option, String item, int avail, int price, int image, int kg) {
        this.option = option;
        this.item = item;
        this.avail = avail;
        this.price = price;
        this.image = image;
        this.kg=kg;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAvail() {
        return avail;
    }

    public void setAvail(int avail) {
        this.avail = avail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }
}

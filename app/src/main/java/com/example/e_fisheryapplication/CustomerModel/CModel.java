package com.example.e_fisheryapplication.CustomerModel;

public class CModel {
    PersonalDetails Personal_details;
    AddressDetails Address_Details;
    OrderPlaced Order_Placed;

    public CModel() {
    }

    public CModel(PersonalDetails Personal_details, AddressDetails Address_Details, OrderPlaced Order_Placed) {
        this.Personal_details = Personal_details;
        this.Address_Details = Address_Details;
        this.Order_Placed = Order_Placed;
    }

    public PersonalDetails getPd() {
        return Personal_details;
    }

    public void setPd(PersonalDetails Personal_details) {
        this.Personal_details = Personal_details;
    }

    public AddressDetails getAd() {
        return Address_Details;
    }

    public void setAd(AddressDetails Address_Details) {
        this.Address_Details = Address_Details;
    }

    public OrderPlaced getOp() {
        return Order_Placed;
    }

    public void setOp(OrderPlaced Order_Placed) {
        this.Order_Placed = Order_Placed;
    }
}

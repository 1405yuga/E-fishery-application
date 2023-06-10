package com.example.e_fisheryapplication.CustomerModel;

public class FsModel {
    String Orderno;
    PersonalDetails Personal_details;
    AddressDetails Address_Details;
    OrderPlaced Order_Placed;

    public FsModel() {
    }

    public FsModel(String orderno, PersonalDetails personal_details, AddressDetails address_Details, OrderPlaced order_Placed) {
        Orderno = orderno;
        Personal_details = personal_details;
        Address_Details = address_Details;
        Order_Placed = order_Placed;
    }

    public String getOrderno() {
        return Orderno;
    }

    public void setOrderno(String orderno) {
        Orderno = orderno;
    }

    public PersonalDetails getPersonal_details() {
        return Personal_details;
    }

    public void setPersonal_details(PersonalDetails personal_details) {
        Personal_details = personal_details;
    }

    public AddressDetails getAddress_Details() {
        return Address_Details;
    }

    public void setAddress_Details(AddressDetails address_Details) {
        Address_Details = address_Details;
    }

    public OrderPlaced getOrder_Placed() {
        return Order_Placed;
    }

    public void setOrder_Placed(OrderPlaced order_Placed) {
        Order_Placed = order_Placed;
    }
}

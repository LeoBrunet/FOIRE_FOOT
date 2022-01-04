package com.foirfoot.model.shop;

public class Address {
    private int id;
    private String street;
    private String city;
    private String postalCode;

    public Address(int id, String street, String city, String postalCode){
        this.id = id;

        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }



    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +

                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}

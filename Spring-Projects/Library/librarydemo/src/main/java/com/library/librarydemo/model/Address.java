package com.library.librarydemo.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
public class Address {
    //------------- Define the fields -------------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "building_number")
    private String buildingNumber;
    @Column(name = "zip_code")
    private String zip_code;
    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();


    //------------- Create constructors -------------//
    public Address(){

    }
    public Address(String country, String city, String street, String buildingNumber, String zip_code) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.zip_code = zip_code;
    }


    //------------- Generate getter/setter methods -------------//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate=creationDate;
    }


    //------------- Generate toString() method -------------//
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", zip_code=" + zip_code +
                ", creationDate=" + creationDate +
                '}';
    }
}

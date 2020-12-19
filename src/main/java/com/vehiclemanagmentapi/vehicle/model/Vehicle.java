package com.vehiclemanagmentapi.vehicle.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vehiclemanagmentapi.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleID;
    private String vehicleName;
    private String brand;
    private String model;
    private int cost;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date date;
    private String description;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonBackReference
    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Vehicle() {
    }

    public Vehicle(String vehicleName, int vehicleID, String brand, String model, int cost, Date date, String description) {
        this.vehicleName = vehicleName;
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.model = model;
        this.cost = cost;
        this.date = date;
        this.description = description;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleName='" + vehicleName + '\'' +
                ", vehicleID='" + vehicleID + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", cost=" + cost +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }



    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}

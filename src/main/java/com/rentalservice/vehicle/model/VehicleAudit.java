package com.rentalservice.vehicle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class VehicleAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vehicleAuditID;
    private int vehicleID;
    private int userID;
    private String operation;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getVehicleAuditID() {
        return vehicleAuditID;
    }

    public void setVehicleAuditID(int vehicleAuditID) {
        this.vehicleAuditID = vehicleAuditID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


}

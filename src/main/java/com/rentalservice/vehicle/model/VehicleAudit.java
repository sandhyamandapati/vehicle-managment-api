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
    private long vehicleAuditID;
    private long vehicleID;
    private long userID;
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

    public long getVehicleAuditID() {
        return vehicleAuditID;
    }

    public void setVehicleAuditID(long vehicleAuditID) {
        this.vehicleAuditID = vehicleAuditID;
    }

    public long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }


}

package com.vehiclemanagmentapi.vehicle.service;

import com.vehiclemanagmentapi.user.model.User;
import com.vehiclemanagmentapi.vehicle.exception.ResourceNotFoundException;
import com.vehiclemanagmentapi.vehicle.model.Vehicle;

import java.util.List;

public interface VehicleService {
     Vehicle  getVehicleById(int vehicleId) throws ResourceNotFoundException;
     List<Vehicle> getAllVehicles();
     Vehicle  saveVehicle(Vehicle vehicle);
     Vehicle  updateVehicle(Vehicle vehicleDetails) throws ResourceNotFoundException;
     void  deleteVehicle(int vehicleId) throws ResourceNotFoundException;
     User assignVehicleToUser(int vehicleId, int userId) throws ResourceNotFoundException;
     User  transferingOwnershipToNewUser(int vehicleId,int userId) throws ResourceNotFoundException;

}

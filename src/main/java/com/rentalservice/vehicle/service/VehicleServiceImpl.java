package com.rentalservice.vehicle.service;

import com.rentalservice.user.model.User;
import com.rentalservice.user.repository.UserRepository;
import com.rentalservice.vehicle.exception.ResourceNotFoundException;
import com.rentalservice.vehicle.model.Vehicle;
import com.rentalservice.vehicle.model.VehicleAudit;
import com.rentalservice.vehicle.repository.VehicleAuditRepository;
import com.rentalservice.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleAuditRepository vehicleAuditRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Vehicle getVehicleById(int vehicleId) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
        return vehicle;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicleDetails) throws ResourceNotFoundException {

        Vehicle vehicle = vehicleRepository.findById(vehicleDetails.getVehicleID())
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found for this id :: " + vehicleDetails.getVehicleID()));

        vehicle.setVehicleName(vehicleDetails.getVehicleName());
        vehicle.setDescription(vehicleDetails.getDescription());
        vehicle.setDate(vehicleDetails.getDate());
        final Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        saveAuditRecord(vehicle);
        return updatedVehicle;
    }

    @Override
    public void deleteVehicle(int vehicleId) throws ResourceNotFoundException  {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found for this id :: " + vehicleId));
        vehicleRepository.delete(vehicle);
    }

    @Override
    public User assignVehicleToUser(int vehicleId, int userId)throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found for this id :: " + vehicleId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
        vehicle.setUser(user);
        vehicle.setDescription(vehicle.getVehicleName() + " is registered to "+user.getFirstName());
        vehicleRepository.save(vehicle);
        saveAuditRecord(vehicle);

        return user;
    }

    @Override
    public User transferingOwnershipToNewUser(int vehicleId, int userId) throws ResourceNotFoundException{
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found for this id :: " + vehicleId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

        vehicle.setUser(user);
        vehicle.setDescription("transferring ownership to new user "+user.getFirstName());

        vehicleRepository.save(vehicle);
        saveAuditRecord(vehicle);
        return user;
    }


    private VehicleAudit saveAuditRecord(Vehicle vehicle){
        VehicleAudit vehicleAudit = new VehicleAudit();
        vehicleAudit.setUserID(vehicle.getUser().getUserID());
        vehicleAudit.setVehicleID(vehicle.getVehicleID());
        vehicleAudit.setOperation(vehicle.getDescription());
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);;
        vehicleAudit.setDate(date);
        VehicleAudit audit = vehicleAuditRepository.save(vehicleAudit);
        return audit;
    }
}

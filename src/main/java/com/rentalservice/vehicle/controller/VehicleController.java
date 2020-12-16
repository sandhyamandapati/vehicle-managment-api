package com.rentalservice.vehicle.controller;

import com.rentalservice.user.model.User;
import com.rentalservice.user.repository.UserRepository;
import com.rentalservice.vehicle.exception.ResourceNotFoundException;
import com.rentalservice.vehicle.model.Vehicle;
import com.rentalservice.vehicle.model.VehicleAudit;
import com.rentalservice.vehicle.repository.VehicleAuditRepository;
import com.rentalservice.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleAuditRepository vehicleAuditRepository;

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity< Vehicle > getVehicleById(@PathVariable(value = "id") Long vehicleId)
            throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
        return ResponseEntity.ok().body(vehicle);
    }

    @PostMapping("/vehicle")
    public Vehicle createVechicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity < Vehicle > updateVehicle(@PathVariable(value = "id") Long vehicleId,
                                                    @Valid @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found for this id :: " + vehicleId));

        vehicle.setVehicleName(vehicleDetails.getVehicleName());
        vehicle.setDescription(vehicleDetails.getDescription());
        vehicle.setDate(vehicleDetails.getDate());
        final Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        VehicleAudit vehicleAudit = new VehicleAudit();
        vehicleAudit.setUserID(vehicle.getUser().getUserID());
        vehicleAudit.setVehicleID(vehicle.getVehicleID());
        vehicleAudit.setFlag(true);
        vehicleAudit.setOperation(vehicleDetails.getDescription());
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);;
        vehicleAudit.setDate(date);
        vehicleAuditRepository.save(vehicleAudit);


        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/vehicles/{id}")
    public Map< String, Boolean > deleteVehicle(@PathVariable(value = "id") Long vehicleId)
            throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found for this id :: " + vehicleId));

        vehicleRepository.delete(vehicle);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/vehicle/{vehicleId}/{userId}")
    public ResponseEntity < Vehicle > assignVehicleToUser(@PathVariable(value = "vehicleId") long vehicleId,@PathVariable(value = "userId") long userId) throws ResourceNotFoundException{
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found for this id :: " + vehicleId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
        vehicle.setUser(user);
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        VehicleAudit vehicleAudit = new VehicleAudit();
        vehicleAudit.setUserID(user.getUserID());
        vehicleAudit.setVehicleID(vehicle.getVehicleID());
        vehicleAudit.setFlag(true);
        vehicleAudit.setOperation("vehicle is assgined to user");

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);;
        vehicleAudit.setDate(date);
        vehicleAuditRepository.save(vehicleAudit);
        return ResponseEntity.ok(updatedVehicle);
    }

    @PutMapping("/transferingOwnershipToNewUser/{vehicleId}/{userId}")
    public ResponseEntity < Vehicle > transferingOwnershipToNewUser(@PathVariable(value = "vehicleId") long vehicleId,
                                                                    @PathVariable(value = "userId") long userId) throws ResourceNotFoundException{
        Vehicle vehicle =git add vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found for this id :: " + vehicleId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

        vehicle.setUser(user);

        final Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        VehicleAudit vehicleAudit = new VehicleAudit();
        vehicleAudit.setUserID(user.getUserID());
        vehicleAudit.setVehicleID(vehicle.getVehicleID());
        vehicleAudit.setFlag(true);
        vehicleAudit.setOperation("vehicle transfered to new user");
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);;
        vehicleAudit.setDate(date);
        vehicleAuditRepository.save(vehicleAudit);

        return ResponseEntity.ok(updatedVehicle);
    }
}

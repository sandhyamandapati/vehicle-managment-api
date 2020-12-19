package com.rentalservice.vehicle.controller;

import com.rentalservice.user.model.User;
import com.rentalservice.vehicle.exception.ResourceNotFoundException;
import com.rentalservice.vehicle.model.Vehicle;
import com.rentalservice.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity< Vehicle > getVehicleById(@PathVariable(value = "id") int vehicleId)
            throws ResourceNotFoundException {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        return ResponseEntity.ok().body(vehicle);
    }

    @PostMapping("/vehicle")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {

        return new ResponseEntity<>(vehicleService.saveVehicle(vehicle), HttpStatus.CREATED);
    }

    @PutMapping("/updateVehicle/{id}")
    public ResponseEntity < Vehicle > updateVehicle(@Valid @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException {
       Vehicle updatedVehicle = vehicleService.updateVehicle(vehicleDetails);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/deleteVehicle/{id}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable(value = "id") int vehicleId)
            throws ResourceNotFoundException {
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/assignVehicleToUser/{vehicleId}/{userId}")
    public ResponseEntity <User> assignVehicleToUser(@PathVariable(value = "vehicleId") Integer vehicleId, @PathVariable(value = "userId") Integer userId) throws ResourceNotFoundException{
        User user = vehicleService.assignVehicleToUser(vehicleId,userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/transferingOwnershipToNewUser/{vehicleId}/{userId}")
    public ResponseEntity < User > transferingOwnershipToNewUser(@PathVariable(value = "vehicleId") int vehicleId,
                                                                    @PathVariable(value = "userId") int userId) throws ResourceNotFoundException{
        User user = vehicleService.transferingOwnershipToNewUser(vehicleId,userId);
        return ResponseEntity.ok(user);
    }
}

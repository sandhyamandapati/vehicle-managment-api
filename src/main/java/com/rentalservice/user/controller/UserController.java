package com.rentalservice.user.controller;

import com.rentalservice.user.model.User;
import com.rentalservice.user.repository.UserRepository;
import com.rentalservice.vehicle.exception.ResourceNotFoundException;
import com.rentalservice.vehicle.model.Vehicle;
import com.rentalservice.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity< User > getVehicleById(@PathVariable(value = "id") long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/user")
    public User createVechicle(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/user/{vehicleId}/{userId}")
    public ResponseEntity < Vehicle > assignVehicleToUser(@PathVariable(value = "vehicleId") long vehicleId,@PathVariable(value = "userId") long userId) throws ResourceNotFoundException{
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found for this id :: " + vehicleId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
        vehicle.setUser(user);
         Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return ResponseEntity.ok(updatedVehicle);
    }


    @PutMapping("/user/{id}")
    public ResponseEntity < User > updateUser(@PathVariable(value = "id") long userID,
                                                     @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userID));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setAddress(userDetails.getAddress());
        user.setDateOfBirth(userDetails.getDateOfBirth());
        user.setLicenceID(userDetails.getLicenceID());

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public Map< String, Boolean > deleteUser(@PathVariable(value = "id") long userID)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userID));

        userRepository.delete(user);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

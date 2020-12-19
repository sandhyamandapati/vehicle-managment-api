package com.vehiclemanagmentapi.user.controller;

import com.vehiclemanagmentapi.user.model.User;
import com.vehiclemanagmentapi.user.service.UserService;
import com.vehiclemanagmentapi.vehicle.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity< User > getVehicleById(@PathVariable(value = "userId") int userId)
            throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/saveUser")
    public ResponseEntity < User > createVechicle(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }


    @PutMapping("/updateUser}")
    public ResponseEntity < User > updateUser(@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
      User updatedUser = userService.updateUser(userDetails);
      return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable(value = "userId") int userId)
            throws ResourceNotFoundException {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

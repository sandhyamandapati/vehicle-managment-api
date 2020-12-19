package com.vehiclemanagmentapi.user.service;

import com.vehiclemanagmentapi.user.model.User;
import com.vehiclemanagmentapi.vehicle.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    User getUserById(int userId) throws ResourceNotFoundException;
    List<User> getAllUsers();
    User  saveUser(User user);
    User  updateUser(User userDetails) throws ResourceNotFoundException;
    void  deleteUser(int userId) throws ResourceNotFoundException;

}

package com.rentalservice.user.service;

import com.rentalservice.user.model.User;
import com.rentalservice.vehicle.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    User getUserById(int userId) throws ResourceNotFoundException;
    List<User> getAllUsers();
    User  saveUser(User user);
    User  updateUser(User userDetails) throws ResourceNotFoundException;
    void  deleteUser(int userId) throws ResourceNotFoundException;

}

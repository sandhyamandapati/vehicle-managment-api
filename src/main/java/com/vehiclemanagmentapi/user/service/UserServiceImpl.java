package com.vehiclemanagmentapi.user.service;

import com.vehiclemanagmentapi.user.model.User;
import com.vehiclemanagmentapi.user.repository.UserRepository;
import com.vehiclemanagmentapi.vehicle.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(int userId) throws ResourceNotFoundException {
          User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
          return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userDetails.getUserID())
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userDetails.getUserID()));

        user.setFirstName(userDetails.getFirstName());
        user.setAddress(userDetails.getAddress());
        user.setLicenceID(userDetails.getLicenceID());

         User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @Override
    public void deleteUser(int userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found for this id :: " + userId));
        userRepository.delete(user);
    }
}

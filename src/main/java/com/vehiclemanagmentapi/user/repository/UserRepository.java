package com.vehiclemanagmentapi.user.repository;

import com.vehiclemanagmentapi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

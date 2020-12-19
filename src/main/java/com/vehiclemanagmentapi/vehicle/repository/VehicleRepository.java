package com.vehiclemanagmentapi.vehicle.repository;

import com.vehiclemanagmentapi.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}

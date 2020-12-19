package com.vehiclemanagmentapi.vehicle.repository;

import com.vehiclemanagmentapi.vehicle.model.VehicleAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleAuditRepository extends JpaRepository<VehicleAudit, Integer> {
}

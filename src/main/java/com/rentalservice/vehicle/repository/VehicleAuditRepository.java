package com.rentalservice.vehicle.repository;

import com.rentalservice.vehicle.model.VehicleAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleAuditRepository extends JpaRepository<VehicleAudit, Integer> {
}

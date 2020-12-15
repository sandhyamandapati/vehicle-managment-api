package com.rentalservice;

import com.rentalservice.vehicle.model.Vehicle;
import com.rentalservice.vehicle.repository.VehicleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class RentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalServiceApplication.class, args);
	}

//		implements CommandLineRunner {
//
//	@Autowired
//	VehicleRepository jpaDataRepository;
//	private Logger logger = LoggerFactory.getLogger(RentalServiceApplication.class);
//
//	public static void main(String[] args) {
//		SpringApplication.run(RentalServiceApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		logger.info("all users {}",jpaDataRepository.findAll());
//        logger.info("updating person {}",jpaDataRepository.save((new Vehicle("car",101, 2008,"tayota", "ttt", 1000, new Date(System.currentTimeMillis()), "dxfcgvhb"))));
//        logger.info("updating person {}",jpaDataRepository.save((new Vehicle("car",102, 2008,"sujiki", "sss", 1000, new Date(System.currentTimeMillis()), "dxfcgvhb"))));
//        logger.info("user 101 details {}",jpaDataRepository.findById(101));
////		jpaDataRepository.deleteById(102);
//////		logger.info("updating person {}",jpaDataRepository.save((new Vehicle("car",103, 2008,"xxx", "dg", 1000, new Date(System.currentTimeMillis()), "dxfcgvhb"))));
//////		logger.info("updating person {}",jpaDataRepository.save((new Vehicle("car",104, 2008,"mazda", "xyz", 1000, new Date(System.currentTimeMillis()), "dxfcgvhb"))));
////////		logger.info("updating person {}",jpaDataRepository.saveAndFlush(new Person(101,"bindu","mumbai")));
////		logger.info("all users {}",jpaDataRepository.findAll());
//	}

}

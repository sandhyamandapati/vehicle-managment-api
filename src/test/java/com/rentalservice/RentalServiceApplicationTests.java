package com.rentalservice;

import com.rentalservice.vehicle.model.Vehicle;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RentalServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RentalServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}

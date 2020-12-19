package com.vehiclemanagmentapi.vehicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehiclemanagmentapi.user.model.User;
import com.vehiclemanagmentapi.vehicle.controller.VehicleController;
import com.vehiclemanagmentapi.vehicle.model.Vehicle;
import com.vehiclemanagmentapi.vehicle.service.VehicleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleServiceImpl vehicleService;

    @Test
    public void getVehicleByIdTest() throws Exception {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("car");
        vehicle.setModel("Tayota");
        vehicle.setBrand("Tata");
        vehicle.setCost(13000);

        when(vehicleService.getVehicleById(anyInt())).thenReturn(vehicle);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/v1/vehicle/{vehicleId}", 1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleName").value("car"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Tayota"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("Tata"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(13000))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllVehiclesTest() throws Exception{
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("car");
        vehicle.setModel("Tayota");
        vehicle.setBrand("Tata");
        vehicle.setCost(13000);
        vehicles.add(vehicle);


        when(vehicleService.getAllVehicles()).thenReturn(vehicles);

        mockMvc.perform( MockMvcRequestBuilders
                    .get("/api/v1/vehicles")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.vehicles").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.vehicles[*].vehicleId").isNotEmpty());

    }

    @Test
    public void saveVehicle() throws Exception {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("car");
        vehicle.setModel("Tayota");
        vehicle.setBrand("Tata");
        vehicle.setCost(13000);

        when(vehicleService.saveVehicle(any(Vehicle.class))).thenReturn(vehicle);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/vehicle")
                .content(new ObjectMapper().writeValueAsString(vehicle))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleID").exists());

    }

    @Test
    public void updateVehicle() throws Exception {

        Vehicle vehicle = new Vehicle();
        vehicle.setModel("Tayota");
        vehicle.setBrand("Tata");
        vehicle.setCost(1200);
        vehicle.setDescription("vehicle is reparied");

        when(vehicleService.updateVehicle(any(Vehicle.class))).thenReturn(vehicle);

        mockMvc.perform(put("/api/v1/updateVehicle/{vehicleId}", 2)
                .content(new ObjectMapper().writeValueAsString(vehicle))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Tayota"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value("1200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("vehicle is reparied"));

    }

    @Test
    public void deleteVehicle() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders.delete("/api/v1/deleteVehicle/{id}", 1) )
                .andExpect(status().isAccepted());
    }

    @Test
    public void assignVehicleToUser() throws Exception {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("bus");
        vehicle.setVehicleID(2);
        vehicle.setModel("Tayota");
        vehicle.setBrand("Tata");
        vehicle.setCost(1200);
        vehicle.setDescription("vehicle is reparied");

        User user = new User();
        user.setUserID(1);
        user.setFirstName("sandhya");
        user.setAddress("Austraila");
        user.setLicenceID("L12GH67");

        mockMvc.perform(put("/api/v1/assignVehicleToUser/{vehicleId}/{userId}",2,1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void transferingOwnershipToNewUser() throws Exception {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("bus");
        vehicle.setVehicleID(2);
        vehicle.setModel("Tayota");
        vehicle.setBrand("Tata");
        vehicle.setCost(1200);
        vehicle.setDescription("vehicle is reparied");

        User user = new User();
        user.setUserID(1);
        user.setFirstName("sandhya");
        user.setAddress("Austraila");
        user.setLicenceID("L12GH67");

        mockMvc.perform(put("/api/v1/transferingOwnershipToNewUser/{vehicleId}/{userId}",2,1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().isOk());
    }

}

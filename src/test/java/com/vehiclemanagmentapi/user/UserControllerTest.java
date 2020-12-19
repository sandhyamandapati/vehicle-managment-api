package com.vehiclemanagmentapi.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehiclemanagmentapi.user.controller.UserController;
import com.vehiclemanagmentapi.user.model.User;
import com.vehiclemanagmentapi.user.service.UserService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUserByIdTest() throws Exception {

        User user = new User();
        user.setFirstName("balu");
        user.setLicenceID("LI5G86ID");
        user.setAddress("sydney,australia");

        when(userService.getUserById(anyInt())).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/user/v1/user/{userId}", 1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("balu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.licenceID").value("LI5G86ID"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("sydney,australia"))
                .andExpect(status().isOk());

    }

    @Test
    public void saveUser() throws Exception {

        User user = new User();
        user.setFirstName("balu");
        user.setLicenceID("LI5G86ID");
        user.setAddress("sydney,australia");

        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/v1/saveUser")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userID").exists());

    }

    @Test
    public void updateUser() throws Exception {

        User user = new User();
        user.setUserID(2);
        user.setFirstName("sandhya");
        user.setAddress("Melborne,australia");
        user.setLicenceID("dfghjk");
        when(userService.updateUser(any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/user/v1/updateUser")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Melborne,australia"));

    }

    @Test
    public void deleteVehicle() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders.delete("/api/user/v1/deleteUser/{userId}", 1) )
                .andExpect(status().isAccepted());
    }
}

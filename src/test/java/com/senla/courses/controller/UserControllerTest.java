package com.senla.courses.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.courses.Application;
import com.senla.courses.dto.JwtRequest;
import com.senla.courses.dto.TrainingDto;
import com.senla.courses.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

    }

    @Test
    public void test_should_return_401() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andDo(print())
                .andExpect(status().is(SC_UNAUTHORIZED));
    }

    @Test
    public void test_should_return_403() throws Exception {
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername("2");
        jwtRequest.setPassword("123_2");

        String body = objectMapper.writeValueAsString(jwtRequest);

        MvcResult result = mockMvc.perform(post("/api/security/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()).andReturn();

        HashMap<String, String> hashMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap.class);
        String token = hashMap.get("access_token");

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUserName("John");
        userDto.setUserEmail("JohnDoe@mail.g");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
                )
                .andDo(print())
                .andExpect(status().is(SC_FORBIDDEN))
                .andExpect(status().reason("Недостаточно прав"))
        ;
    }

    @Test
    public void test_should_return_user() throws Exception {
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername("1");
        jwtRequest.setPassword("123_1");

        String body = objectMapper.writeValueAsString(jwtRequest);

        MvcResult result = mockMvc.perform(post("/api/security/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()).andReturn();

        HashMap<String, String> hashMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap.class);
        String token = hashMap.get("access_token");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")
                        .header("Authorization", "Bearer " + token)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.userName").value("Иван"))
                .andExpect(jsonPath("$.userEmail").value("test@mail.ru"))
        ;
    }

    @Test
    public void test_should_create_user() throws Exception {
        //токен админа
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername("1");
        jwtRequest.setPassword("123_1");

        String body = objectMapper.writeValueAsString(jwtRequest);

        MvcResult result = mockMvc.perform(post("/api/security/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()).andReturn();

        HashMap<String, String> hashMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap.class);
        String token = hashMap.get("access_token");

        UserDto userDto = new UserDto();
        userDto.setUserName("John Doe");
        userDto.setUserEmail("johndoe@gmail.ru");
        List<TrainingDto> trainings = new ArrayList<>();
        TrainingDto trainingdto = new TrainingDto();
        trainingdto.setId(1L);
        trainingdto.setTrainingName("аэробная тренировка");
        trainings.add(trainingdto);
        userDto.setTrainings(trainings);

        mockMvc.perform(post("/api/users")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.userName").value(userDto.getUserName()))
                .andExpect(jsonPath("$.userEmail").value(userDto.getUserEmail()))
                .andExpect(jsonPath("$.trainings[0].id").value(trainingdto.getId()))
                .andExpect(jsonPath("$.trainings[0].trainingName").value(trainingdto.getTrainingName()))
        ;
    }
}

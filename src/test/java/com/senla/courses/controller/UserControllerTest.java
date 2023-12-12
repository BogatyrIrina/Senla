package com.senla.courses.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.courses.Application;
import com.senla.courses.dto.TrainingDto;
import com.senla.courses.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration("classpath:META-INF/web-resources")
@ContextHierarchy(@ContextConfiguration(classes = Application.class))
public class UserControllerTest {
    @Autowired
    UserController userController;
    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void test_should_return_user() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.userName").value("Иван"))
                .andExpect(jsonPath("$.userEmail").value("test@mail.ru"))
        ;
    }

    @Test
    public void test_should_create_user() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserName("John Doe");
        userDto.setUserEmail("johndoe@gmail.ru");
        List<TrainingDto> trainings = new ArrayList<>();
        TrainingDto trainingdto = new TrainingDto();
        trainingdto.setId(1L);
        trainingdto.setTrainingName("аэробная тренировка");
        trainings.add(trainingdto);
        userDto.setTrainings(trainings);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
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

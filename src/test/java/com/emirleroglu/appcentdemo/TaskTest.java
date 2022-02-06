package com.emirleroglu.appcentdemo;


import com.emirleroglu.appcentdemo.controller.TaskController;
import com.emirleroglu.appcentdemo.model.AppUser;
import com.emirleroglu.appcentdemo.model.Task;

import com.emirleroglu.appcentdemo.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Date;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskTest.class)
@ContextConfiguration
public class TaskTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TaskController controller;
    @Mock
    TaskRepository repository;
    @Mock
    Task updateTask;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @WithMockUser
    public void createTest() throws Exception {
        Task task = new Task();
        AppUser user = new AppUser();
        user.setId(1L);
        task.setUser(user);
        task.setDescription("Test Description");
        task.setComplated(false);
        Date date = new Date();
        task.setTaskDate(date);
        task.setId(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/task/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/getTasks")
                        .param("userId", "1"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void deleteTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/task/delete")
                .param("id", "2")).andExpect(status().isOk());
    }
    /*
    @Test
    @WithMockUser
    public void updateTask() throws Exception{
        Task task = new Task();
        AppUser user = new AppUser();
        user.setId(1L);
        task.setUser(user);
        task.setDescription("Test Description");
        task.setComplated(true);
        Date date = new Date();
        task.setTaskDate(date);
        task.setId(2L);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/task/update")
                        .param("id","1L")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(task)))
                        .andExpect(status().isOk());

    }
    */


}

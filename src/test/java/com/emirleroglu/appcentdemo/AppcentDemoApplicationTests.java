package com.emirleroglu.appcentdemo;

import com.emirleroglu.appcentdemo.controller.AuthController;
import com.emirleroglu.appcentdemo.controller.TaskController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AppcentDemoApplicationTests {
    @Autowired
    private AuthController authController;
    @Autowired
    private TaskController taskController;

    @Test
    void contextLoads() {
        assertThat(authController).isNotNull();
        assertThat(taskController).isNotNull();
    }


}

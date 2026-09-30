package com.cloudalpha.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void healthReturnsOk() throws Exception {
        mvc.perform(get("/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void greetingUsesTheNameParameter() throws Exception {
        mvc.perform(get("/api/greeting").param("name", "Raj"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Hello, Raj!"));
    }

    @Test
    void greetingDefaultsToWorld() throws Exception {
        mvc.perform(get("/api/greeting"))
            .andExpect(jsonPath("$.message").value("Hello, world!"));
    }

    @Test
    void unknownRouteReturns404() throws Exception {
        mvc.perform(get("/nope")).andExpect(status().isNotFound());
    }
}

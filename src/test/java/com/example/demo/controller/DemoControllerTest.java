package com.example.demo.controller;


import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DemoControllerTest extends BaseControllerTest {

    private static final String DEMO_ENDPOINT = "/api/v1/demo";

    @Test
    void getDemoTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(DEMO_ENDPOINT + "/-1").accept(APPLICATION_JSON);
        mvc.perform(requestBuilder)
            .andExpect(jsonPath("$.id").value(-1))
            .andExpect(jsonPath("$.name").value("11"))
            .andExpect(jsonPath("$.code").value("1111-11-1111"))
            .andExpect(status().isOk());
    }

    @Test
    void getDemoListTest() throws Exception {
        MockHttpServletRequestBuilder getAllRequestBuilder = MockMvcRequestBuilders.get(DEMO_ENDPOINT).accept(APPLICATION_JSON);
        mvc.perform(getAllRequestBuilder)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)));
    }

}

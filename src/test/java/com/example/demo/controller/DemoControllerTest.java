package com.example.demo.controller;


import com.example.demo.dto.DemoRequestDto;
import org.junit.jupiter.api.Order;
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
    @Order(1)
    void getDemoTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(DEMO_ENDPOINT + "/-1").accept(APPLICATION_JSON);
        mvc.perform(requestBuilder)
            .andExpect(jsonPath("$.id").value(-1))
            .andExpect(jsonPath("$.name").value("11"))
            .andExpect(jsonPath("$.code").value("1111-11-1111"))
            .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void getDemoListTest() throws Exception {
        MockHttpServletRequestBuilder getAllRequestBuilder = MockMvcRequestBuilders.get(DEMO_ENDPOINT).accept(APPLICATION_JSON);
        mvc.perform(getAllRequestBuilder)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    @Order(3)
    void createDemoTest() throws Exception {

        DemoRequestDto demoRequestDto = new DemoRequestDto();
        demoRequestDto.setName("99");
        demoRequestDto.setCode("999911119999");

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .post(DEMO_ENDPOINT)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .content(mapper.writeValueAsString(demoRequestDto));

        mvc.perform(requestBuilder)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("99"))
            .andExpect(jsonPath("$.code").value("999911119999"))
            .andExpect(status().isCreated());
    }

    @Test
    @Order(4)
    void updateDemoTest() throws Exception {

        DemoRequestDto demoRequestDto = new DemoRequestDto();
        demoRequestDto.setName("88");
        demoRequestDto.setCode("888811118888");

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .put(DEMO_ENDPOINT + "/-1")
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .content(mapper.writeValueAsString(demoRequestDto));

        mvc.perform(requestBuilder)
            .andExpect(jsonPath("$.id").value(-1))
            .andExpect(jsonPath("$.name").value("88"))
            .andExpect(jsonPath("$.code").value("888811118888"))
            .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void deleteDemoTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(DEMO_ENDPOINT + "/-1").accept(APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk());
    }

}

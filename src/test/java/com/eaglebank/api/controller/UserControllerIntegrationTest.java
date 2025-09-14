package com.eaglebank.api.controller;

import com.eaglebank.api.util.JsonSource;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @ParameterizedTest
    @JsonSource(file="json/createUser.json")
    public void shouldSuccessfullyCreateUser(String json) throws Exception {
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", Matchers.matchesPattern("^usr-[A-Za-z0-9]+$")))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andExpect(jsonPath("$.address").isNotEmpty())
                .andExpect(jsonPath("$.address.line1").value("32 test lane"))
                .andExpect(jsonPath("$.address.town").value("testville"))
                .andExpect(jsonPath("$.address.county").value("testonia"))
                .andExpect(jsonPath("$.address.postcode").value("T3 5TS"));
    }

    @ParameterizedTest
    @JsonSource(file="json/createUserWithMissingMandatoryFields.json")
    public void shouldReturnErrorOnCreateUserIfMandatoryFieldsAreMissing(String json) throws Exception {
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message").value("Invalid request body"))
                .andExpect(jsonPath("$.details.[0].field").value("phoneNumber"))
                .andExpect(jsonPath("$.details.[0].message").value("must not be blank"))
                .andExpect(jsonPath("$.details.[1].field").value("name"))
                .andExpect(jsonPath("$.details.[1].message").value("must not be blank"));
    }

    @ParameterizedTest
    @JsonSource(file="json/createUserWithIncorrectlyFormattedFields.json")
    public void shouldReturnErrorOnCreateUserWithIncorrectlyFormattedFields(String json) throws Exception {
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message").value("Invalid request body"))
                .andExpect(jsonPath("$.details.[0].field").value("phoneNumber"))
                .andExpect(jsonPath("$.details.[0].message").value("must match \"^\\+[1-9]\\d{1,14}$\""))
                .andExpect(jsonPath("$.details.[1].field").value("email"))
                .andExpect(jsonPath("$.details.[1].message").value("must be a well-formed email address"));
    }





}
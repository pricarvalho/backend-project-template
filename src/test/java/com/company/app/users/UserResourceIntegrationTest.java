package com.company.app.users;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("/users")
public class UserResourceIntegrationTest {

    @Autowired
    private UserResource userResource;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    class HappyPath {

        private String userId = null;

        @Test
        @Order(1)
        void save_whenCreateAnUser_returns201() throws Exception {
            final String body = objectMapper.writeValueAsString(
                    new UserRequest("Integration", "Test", "integration@test.com.br"));

            final MvcResult response = mvc.perform(post("/users")
                    .contentType(APPLICATION_JSON)
                    .content(body))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("firstName").value("Integration"))
                    .andExpect(jsonPath("lastName").value("Test"))
                    .andExpect(jsonPath("email").value("integration@test.com.br"))
                    .andReturn();

            this.userId = objectMapper.readValue(response.getResponse().getContentAsString(), UserResponse.class)
                    .getId();
        }

        @Test
        @Order(2)
        public void get_whenGetUser_returns200() throws Exception {
            mvc.perform(get("/users/" + userId)
                    .contentType(APPLICATION_JSON))
                    .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("id").value(userId))
                    .andExpect(jsonPath("firstName").value("Integration"))
                    .andExpect(jsonPath("lastName").value("Test"))
                    .andExpect(jsonPath("email").value("integration@test.com.br"));
        }

        @Test
        @Order(3)
        public void delete_whenDeleteUser_returns204() throws Exception {
            mvc.perform(delete("/users/" + userId))
                    .andExpect(status().isNoContent());
        }

    }

}

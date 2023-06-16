package com.company.app.meetups;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import lombok.Data;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("/meetups")
public class MeetupsIntegrationTest {

    @Autowired
    private MeetupResource meetupResource;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
    class HappyPath {

        private String meetupId = null;

        @Test
        @Order(1)
        void save_whenCreateMeetup_returns201() throws Exception {
            final String body = objectMapper.writeValueAsString(
                    new MeetupRequest("Keycloak Project Management", "http://blabla", "1000 Bandeiras Avenue", "Learning about keycloak and react"));

            final MvcResult response = mvc.perform(post("/meetups")
                    .contentType(APPLICATION_JSON)
                    .content(body))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("data.id").isNotEmpty())
                    .andExpect(jsonPath("data.title").value("Keycloak Project Management"))
                    .andExpect(jsonPath("data.image").value("http://blabla"))
                    .andExpect(jsonPath("data.address").value("1000 Bandeiras Avenue"))
                    .andExpect(jsonPath("data.description").value("Learning about keycloak and react"))
                    .andReturn();

            this.meetupId = objectMapper.readValue(response.getResponse().getContentAsString(), MeetupResponseTest.class).getData().getId();
            assertNotNull(meetupId, "Created test didn't return the id");    
        }

        @Test
        @Order(2)
        public void get_whenGetMeetup_returns200() throws Exception {
            mvc.perform(get("/meetups/"+meetupId)
                    .contentType(APPLICATION_JSON))
                    .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("data.title").value("Keycloak Project Management"))
                    .andExpect(jsonPath("data.image").value("http://blabla"))
                    .andExpect(jsonPath("data.address").value("1000 Bandeiras Avenue"))
                    .andExpect(jsonPath("data.description").value("Learning about keycloak and react"));
        }

        @Test
        @Order(3)
        public void update_whenUpateMeetup_returns200() throws Exception {
            final String body = objectMapper.writeValueAsString(
                    new MeetupRequest("Keycloak Project Management", "http://blabla", "8000 Bandeiras Avenue", "Learning about keycloak and react"));

            mvc.perform(put("/meetups/"+ meetupId)
                    .contentType(APPLICATION_JSON)
                    .content(body))
                    .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("data.id").value(meetupId))
                    .andExpect(jsonPath("data.title").value("Keycloak Project Management"))
                    .andExpect(jsonPath("data.image").value("http://blabla"))
                    .andExpect(jsonPath("data.address").value("8000 Bandeiras Avenue"))
                    .andExpect(jsonPath("data.description").value("Learning about keycloak and react"));
        }

        @Test
        @Order(4)
        public void get_whenGetAllMeetups_returns200() throws Exception {
            mvc.perform(get("/meetups")
                    .contentType(APPLICATION_JSON))
                    .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("data").isNotEmpty());
        }

        @Test
        @Order(5)
        public void delete_whenDeleteUser_returns204() throws Exception {
            mvc.perform(delete("/meetups/"+meetupId))
                    .andExpect(status().isNoContent());
        }

    }

    @Data
    private static class MeetupResponseTest {

        MeetupResponse data;

        public MeetupResponseTest() {

        }

    }

}

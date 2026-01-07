package org.tonality;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TonalityAppTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.service").value("AI_Tonality"))
                .andExpect(jsonPath("$.version").value("1.0"));
    }

    @Test
    void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("AI Tonality Analysis Service")));
    }

    @Test
    void testTestEndpoint() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("DIRECT TEST")));
    }

    @Test
    void testTonalityAnalysisGet() throws Exception {
        mockMvc.perform(get("/api/tonality")
                        .param("text", "I love this amazing service!"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sentiment").value("positive"))
                .andExpect(jsonPath("$.confidence").isNumber())
                .andExpect(jsonPath("$.analyzedText").value("I love this amazing service!"))
                .andExpect(jsonPath("$.version").value("1.0"));
    }

    @Test
    void testTonalityAnalysisPost() throws Exception {
        String jsonRequest = "{\"text\": \"This is terrible and awful\"}";

        mockMvc.perform(post("/api/tonality")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sentiment").value("negative"));
    }

    @Test
    void testActuatorEndpoints() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/actuator/info"))
                .andExpect(status().isOk());
    }

    @Test
    void testInvalidRequests() throws Exception {
        mockMvc.perform(get("/api/tonality"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post("/api/tonality")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sentiment").value("neutral"))
                .andExpect(jsonPath("$.confidence").value(0.5));
    }
}
package technical.test.application.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import technical.test.application.models.HealthcheckResponseModel;
import technical.test.application.services.HealthcheckService;
import technical.test.application.utils.TestData;

@WebMvcTest(
        controllers = HealthcheckController.class
)
public class HealthcheckControllerTests extends TestData {
    private MockMvc mockMvc;

    @Autowired
    private HealthcheckController healthcheckController;

    @MockBean
    private HealthcheckService healthcheckService;

    @BeforeEach
    public void setUp() {
        this.mockMvc =
                MockMvcBuilders.standaloneSetup(healthcheckController).build();
    }

    @Test
    void validateHealthcheckEndpointResponse() throws Exception {
        when(healthcheckService.getHealthcheckResponse())
                .thenReturn(
                        new HealthcheckResponseModel(
                                VERSION,
                                DESCRIPTION,
                                COMMIT_SHA
                        )
                );

        mockMvc
                .perform(
                        get("/healthcheck"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(DESCRIPTION))
                .andExpect(MockMvcResultMatchers.jsonPath("$.version").value(VERSION))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastCommitSha").value(COMMIT_SHA));
    }

    @Test
    void unknownEndpointReturns404() throws Exception {
        mockMvc
                .perform(get("/healthcheckz"))
                .andExpect(status().isNotFound());
    }

}

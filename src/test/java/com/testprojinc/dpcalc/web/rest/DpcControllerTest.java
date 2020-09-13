package com.testprojinc.dpcalc.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DpcControllerTest {

    public static final String BASE_PATH = "/api/v1";

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testCalculatePoints() throws Exception {
        final String calculateRequestFormat = "/sport/%s/result/%s";

        mockMvc.perform(
                get(BASE_PATH + String.format(calculateRequestFormat, "100 m", 10.395)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.points").value(1000));

        mockMvc.perform(
                get(BASE_PATH + String.format(calculateRequestFormat, "long jump", 7.76)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.points").value(1000));

        mockMvc.perform(
                get(BASE_PATH + String.format(calculateRequestFormat, "1500 m", "4:21.77")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.points").value(800));

        mockMvc.perform(
                get(BASE_PATH + String.format(calculateRequestFormat, "1500", "1")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorType").value("IllegalArgumentException"))
                .andExpect(jsonPath("$.errorMessage").value(startsWith("Unknown sport")));
    }

}

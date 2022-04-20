package com.mercadolibre.mutantes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.mutantes.models.ResponseMutant;
import com.mercadolibre.mutantes.models.ResponseStats;
import com.mercadolibre.mutantes.service.MutantService;
import com.mercadolibre.mutantes.models.RequestMutant;
import com.mercadolibre.mutantes.util.Constantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MutantController.class)
class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MutantService mutantService;

    private ResponseMutant responseMutant;
    private ResponseStats responseStatus;

    @BeforeEach
    void setUp() {
        responseMutant = new ResponseMutant();
        responseMutant.setMutant(false);
        responseMutant.setAdn(Constantes.matrizMutant);
        responseMutant.setMensaje("xd");

        responseStatus = new ResponseStats();
        responseStatus.setCount_mutant_dna(2);
        responseStatus.setCount_human_dna(4);
        responseStatus.setRatio(0.5);
    }

    @Test
    public void testMutantEndpoint() throws Exception {
        when(mutantService.isMutant(Mockito.any())).thenReturn(responseMutant);
        RequestMutant requestMutant = new RequestMutant();
        requestMutant.setDna(responseMutant.getAdn());
        mockMvc.perform(post("/mutant")
                        .content(objectMapper.writeValueAsString(requestMutant))
                        .contentType("application/json"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testStatsEndpoint() throws Exception {
        when(mutantService.stats()).thenReturn(responseStatus);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/stats")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
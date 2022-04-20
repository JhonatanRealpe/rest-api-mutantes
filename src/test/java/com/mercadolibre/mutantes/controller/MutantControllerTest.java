package com.mercadolibre.mutantes.controller;

import com.mercadolibre.mutantes.models.ResponseMutant;
import com.mercadolibre.mutantes.models.ResponseStats;
import com.mercadolibre.mutantes.service.MutantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
class MutantControllerTest {

    private boolean isMutant = true;
    private String mensaje = "ASASDASD";
    private String[] adn = {"AGGG,CCGG,TGAT,GGAG"};
    private ResponseMutant responseMutant;
    private ResponseStats responseStats;
    @MockBean
    MutantService mutantService;

    //@Autowired
    //private WebTestClient webTestClient; // Por dependencia falla

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        responseMutant = new ResponseMutant();
        responseMutant.setMutant(isMutant);
        responseMutant.setAdn(adn);
        responseMutant.setMensaje(mensaje);

        //Mockito.when(mutantService.isMutant(Mockito.any())).thenReturn(responseMutant);

        responseStats = new ResponseStats();
        responseStats.setCount_mutant_dna(10);
        responseStats.setCount_human_dna(20);
        responseStats.setRatio(1.0);

        Mockito.when(mutantService.stats()).thenReturn(responseStats);
    }

    @Test
    void verificarAdnTest() throws Exception{

    }

    @Test
    void statsTests() {
        /*webTestClient.get()
                .uri("/stats")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.count_mutant_dna")
                .isEqualTo("10");*/
    }


}
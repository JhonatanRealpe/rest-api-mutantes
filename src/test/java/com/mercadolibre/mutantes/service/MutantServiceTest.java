package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.models.AdnsDTO;
import com.mercadolibre.mutantes.models.ResponseMutant;
import com.mercadolibre.mutantes.models.ResponseStats;
import com.mercadolibre.mutantes.repositories.IAdnsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MutantServiceTest {
    private boolean isMutant = true;
    private String mensaje = "ADN de mutante!";
    private String[] adn = {"ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCGA,TCACTG"};
    private ResponseMutant responseMutant;
    private ResponseStats responseStats;
    private AdnsDTO adnsDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        responseMutant = new ResponseMutant();
        responseMutant.setMutant(isMutant);
        responseMutant.setAdn(adn);
        responseMutant.setMensaje(mensaje);

        responseStats =  new ResponseStats();
        responseStats.setCount_human_dna(10);
        responseStats.setCount_mutant_dna(5);

        adnsDTO = new AdnsDTO();
        adnsDTO.setAdn(adn);
        adnsDTO.setMutant(true);
        adnsDTO.set_id("1");
    }

    @Mock
    private IAdnsRepository adnsRepository;

    @Mock
    private Validate validateService;

    @InjectMocks
    private MutantService mutantService;

    @Test
    void isMutant() {
        Mockito.when(validateService.isValidate(Mockito.any())).thenReturn(responseMutant);
        Mockito.when(adnsRepository.save(Mockito.any())).thenReturn(adnsDTO);

        ResponseMutant myMutant = mutantService.isMutant(adn);

        assertNotNull(myMutant.getMensaje());
        assertNotNull(myMutant);
        Mockito.verify(validateService).isValidate(Mockito.any());
        Mockito.verify(adnsRepository).save(Mockito.any(AdnsDTO.class));
    }

    @Test
    void exitAdn(){
        Mockito.when(adnsRepository.findByAdn(Mockito.any())).thenReturn(Optional.ofNullable(adnsDTO));
        Boolean exist = mutantService.existAdn(adn);
        assertNotNull(adnsDTO.get_id());
        assertNotNull(adnsDTO.isMutant());
        assertNotNull(exist);
    }

    @Test
    void stats(){
        ResponseStats myMutant = mutantService.stats();
        assertNotNull(myMutant);
    }

    @Test
    void ratio(){
        mutantService.ratio(responseStats);
        assertNotNull(responseStats.getRatio());
    }
}
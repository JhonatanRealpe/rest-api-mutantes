package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.models.ResponseMutant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {

    @Autowired
    Validate validate = new Validate();
    ResponseMutant responseMutant;
    String[] matriz = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCGA","TCACTG"};
    boolean isMatriz;
    boolean isLetras;
    boolean isMutant;
    String[][] convertMatriz;

    @Test
    void isValidate() {
        responseMutant = validate.isValidate(matriz);
        Assertions.assertTrue(responseMutant.isMutant());
    }

    @Test
    void isMatriz() {
        isMatriz = validate.isMatriz(matriz);
        Assertions.assertTrue(isMatriz);
    }

    @Test
    void isLetras() {
        isLetras = validate.isMatriz(matriz);
        Assertions.assertTrue(isLetras);
    }

    @Test
    void convertAdnToMatriz() {
        convertMatriz = validate.convertAdnToMatriz(matriz);
        Assertions.assertEquals(String.valueOf(matriz[0].charAt(0)), convertMatriz[0][0]);
    }

    @Test
    void isMutant() {
        isMutant = validate.isMatriz(matriz);
        Assertions.assertTrue(isMutant);
    }
}
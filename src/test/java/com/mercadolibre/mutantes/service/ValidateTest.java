package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.models.ResponseMutant;
import com.mercadolibre.mutantes.util.Constantes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {

    @Autowired
    Validate validate = new Validate();
    ResponseMutant responseMutant;
    boolean isMatriz;
    boolean isLetras;
    boolean isMutant;
    String[][] convertMatriz;

    @Test
    void isValidate() {
        responseMutant = validate.isValidate(Constantes.matrizMutant);
        Assertions.assertTrue(responseMutant.isMutant());
    }

    @Test
    void isValidateMatrizInvalid() {
        responseMutant = validate.isValidate(Constantes.matrizInvalid);
        Assertions.assertFalse(responseMutant.isMutant());
    }
    @Test
    void isValidateLetrasInvalid() {
        responseMutant = validate.isValidate(Constantes.LetrasInvalid);
        Assertions.assertFalse(responseMutant.isMutant());
    }
    @Test
    void isValidateIsMutant() {
        responseMutant = validate.isValidate(Constantes.matrizHuman);
        Assertions.assertFalse(responseMutant.isMutant());
    }

    @Test
    void isMatriz() {
        isMatriz = validate.isMatriz(Constantes.matrizMutant);
        Assertions.assertTrue(isMatriz);
    }

    @Test
    void isLetras() {
        isLetras = validate.isMatriz(Constantes.matrizMutant);
        Assertions.assertTrue(isLetras);
    }

    @Test
    void convertAdnToMatriz() {
        convertMatriz = validate.convertAdnToMatriz(Constantes.matrizMutant);
        Assertions.assertEquals(String.valueOf(Constantes.matrizMutant[0].charAt(0)), convertMatriz[0][0]);
    }

    @Test
    void isMutant() {
        isMutant = validate.isMatriz(Constantes.matrizMutant);
        Assertions.assertTrue(isMutant);
    }
}
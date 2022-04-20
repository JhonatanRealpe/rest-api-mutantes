package com.mercadolibre.mutantes.util;

import com.mercadolibre.mutantes.models.ResponseMutant;

public class Constantes {

    public static final boolean isMutant = true;
    public static final String mensajeMutante = "ADN de mutante!";
    public static final String mensajeHuman = "ADN de Humano!";
    public static final String mensajeMatrizInvalid = "Matriz invalida, por favor verifique!";
    public static final String mensajeLetrasInvalid = "Letras invalidas solo pueden ser: (A,T,C,G), por favor verifique!";
    public static final String[] matrizMutant = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCGA","TCACTG"};
    public static final String[] matrizHuman = {"AGGG","CCGG","TGAT","GGAG"};
    public static final String[] matrizInvalid = {"ATGCG","CAGTGC","TTATGT"};
    public static final String[] LetrasInvalid = {"ATG","CAG","TTZ"};
    public static final String[] letrasValidas = { "A", "T", "C", "G" };


}

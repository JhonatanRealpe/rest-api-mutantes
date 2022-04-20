package com.mercadolibre.mutantes.service;

import java.util.Arrays;

import com.mercadolibre.mutantes.util.Constantes;
import org.springframework.stereotype.Component;

import com.mercadolibre.mutantes.models.ResponseMutant;

@Component
public class Validate {

	private ResponseMutant responseMutant = new ResponseMutant();
	public ResponseMutant isValidate(String[] matriz) {
		if (!isMatriz(matriz)) {
			responseMutant.setMensaje(Constantes.mensajeMatrizInvalid);
			responseMutant.setMutant(false);
		} else if (!isLetras(matriz)) {
			responseMutant.setMensaje(Constantes.mensajeLetrasInvalid);
			responseMutant.setMutant(false);
		} else if (!isMutant(convertAdnToMatriz(matriz))) {
			responseMutant.setMensaje(Constantes.mensajeHuman);
			responseMutant.setMutant(false);
			responseMutant.setAdn(matriz);
		} else {
			responseMutant.setMensaje(Constantes.mensajeMutante);
			responseMutant.setMutant(true);
			responseMutant.setAdn(matriz);
		}

		return responseMutant;
	}

	public boolean isMatriz(String[] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			if (!(matriz[i].length() == matriz.length)) {
				return false;
			}
		}
		return true;
	}

	public boolean isLetras(String[] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			String f = matriz[i];
			String[] fila = f.split("");
			for (int j = 0; j < fila.length; j++) {
				boolean containsLetra = Arrays.stream(Constantes.letrasValidas).anyMatch(fila[j].toUpperCase()::equals);
				if (!containsLetra) {
					return false;
				}
			}
		}
		return true;
	}

	public String[][] convertAdnToMatriz(String[] adn) {
		String[][] matriz = new String[adn.length][adn.length];
		for (int i = 0; i < adn.length; i++) {
			String f = adn[i];
			String[] fila = f.split("");
			for (int j = 0; j < fila.length; j++) {
				matriz[i][j] = fila[j];
			}
		}
		return matriz;
	}

	public boolean isMutant(String[][] matriz) {
		boolean mutant = false;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length - 3; j++) {
				// Horizontal
				if (matriz[i][j].equals(matriz[i][j + 1]) && matriz[i][j].equals(matriz[i][j + 2])
						&& matriz[i][j].equals(matriz[i][j + 3])) {
					mutant = true;
				}
				// Vertical
				if (matriz[j][i].equals(matriz[j + 1][i]) && matriz[j][i].equals(matriz[j + 2][i])
						&& matriz[j][i].equals(matriz[j + 3][i])) {
					mutant = true;
				}
				// Diagonal
				if (matriz[0].length - 3 > i && matriz[i][j].equals(matriz[i + 1][j + 1])
						&& matriz[i][j].equals(matriz[i + 2][j + 2]) && matriz[i][j].equals(matriz[i + 3][j + 3])) {
					mutant = true;
				}
			}
		}
		return mutant;
	}

}

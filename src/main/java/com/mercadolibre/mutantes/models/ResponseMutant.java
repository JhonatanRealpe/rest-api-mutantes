package com.mercadolibre.mutantes.models;

public class ResponseMutant {

	private boolean isMutant;
	private String mensaje;
	private String[] adn;
	
	
	public boolean isMutant() {
		return isMutant;
	}
	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String[] getAdn() {
		return adn;
	}
	public void setAdn(String[] adn) {
		this.adn = adn;
	}
	
	
	
}

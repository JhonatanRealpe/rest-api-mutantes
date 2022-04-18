package com.mercadolibre.mutantes.models;

public class ResponseStats {
	
	private int count_mutant_dna = 0;
	private int count_human_dna = 0;
	private Double ratio = (double) 0;
	
	public int getCount_mutant_dna() {
		return count_mutant_dna;
	}
	public void setCount_mutant_dna(int count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	public int getCount_human_dna() {
		return count_human_dna;
	}
	public void setCount_human_dna(int count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	public Double getRatio() {
		return ratio;
	}
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	
	
}

package com.mercadolibre.mutantes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutantes.models.AdnsDTO;
import com.mercadolibre.mutantes.models.ResponseMutant;
import com.mercadolibre.mutantes.models.ResponseStats;
import com.mercadolibre.mutantes.repositories.IAdns;

@Service
public class MutanteService {

	@Autowired
	private IAdns adns;
	@Autowired
	private Validate validate;
	private ResponseMutant responseMutant;
	private AdnsDTO adnsDTO;
	private ResponseStats responseStats;

	public ResponseMutant isMutant(String[] adn) {

		responseMutant = validate.isValidate(adn);

		if (responseMutant.getAdn() != null && !existAdn(responseMutant.getAdn())) {
			adnsDTO = new AdnsDTO();
			adnsDTO.setAdn(responseMutant.getAdn());
			adnsDTO.setMutant(responseMutant.isMutant());
			adns.save(adnsDTO);
		}

		return responseMutant;
	}

	public boolean existAdn(String[] adn) {
		Optional<AdnsDTO> dd = adns.findByAdn(adn);
		if (dd.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public ResponseStats stats() {
		responseStats = new ResponseStats();
		List<AdnsDTO> listStats;
		listStats = adns.countByMutants(true);

		if (listStats != null && !listStats.isEmpty())
			responseStats.setCount_mutant_dna(listStats.size());

		listStats = adns.countByMutants(false);
		if (listStats != null && !listStats.isEmpty())
			responseStats.setCount_human_dna(listStats.size());

		ratio(responseStats);

		return responseStats;
	}

	public void ratio(ResponseStats responseStats) {
		double countHuman = responseStats.getCount_human_dna();
		double countMutant = responseStats.getCount_mutant_dna();
		if (countHuman > 0 && countMutant > 0) {
			responseStats.setRatio(countMutant / countHuman);
		}
	}

}

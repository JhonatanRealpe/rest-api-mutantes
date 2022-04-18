package com.mercadolibre.mutantes.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutantes.models.ResponseMutant;
import com.mercadolibre.mutantes.models.ResponseStats;
import com.mercadolibre.mutantes.service.MutanteService;

@RestController
@RequestMapping("")
public class MutantesController {
	
	@Autowired
	private MutanteService mutanteService;
	private ResponseMutant responseMutant;
	private ResponseStats responseStats;

	@PostMapping("/mutant")
	public ResponseEntity<?> verificarAdn(@RequestParam String[] adn) {
		responseMutant = mutanteService.isMutant(adn);
		JSONObject resp = new JSONObject();
		resp.put("mensaje", responseMutant.getMensaje());
		
		if (responseMutant.isMutant()) {
			return new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
		} else {	
			return new ResponseEntity<String>(resp.toString(), HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/stats")
	public ResponseEntity<?> stats() {
		responseStats = mutanteService.stats();
		JSONObject resp = new JSONObject();
		resp.put("count_mutant_dna", responseStats.getCount_mutant_dna());
		resp.put("count_human_dna", responseStats.getCount_human_dna());
		resp.put("ratio", responseStats.getRatio());
		return new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
	}
}

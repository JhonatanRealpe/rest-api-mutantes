package com.mercadolibre.mutantes.controller;

import com.mercadolibre.mutantes.models.RequestMutant;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mercadolibre.mutantes.models.ResponseMutant;
import com.mercadolibre.mutantes.models.ResponseStats;
import com.mercadolibre.mutantes.service.MutantService;

import javax.validation.Valid;

@RestController
@RequestMapping("")
public class MutantController {
	
	@Autowired
	private MutantService mutantService;
	private ResponseMutant responseMutant;
	private ResponseStats responseStats;

	@PostMapping("/mutant")
	public ResponseEntity<?> mutant(@Valid @RequestBody RequestMutant dna) {
		responseMutant = mutantService.isMutant(dna.getDna());
		JSONObject resp = new JSONObject();
		resp.put("mensaje", responseMutant.getMensaje());
		
		if (responseMutant.isMutant()) {
			return new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
		} else {	
			return new ResponseEntity<String>(resp.toString(), HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/stats")
	public ResponseEntity<?> stats(){
		responseStats = mutantService.stats();
		JSONObject resp = new JSONObject();
		resp.put("count_mutant_dna", responseStats.getCount_mutant_dna());
		resp.put("count_human_dna", responseStats.getCount_human_dna());
		resp.put("ratio", responseStats.getRatio());
		return new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
	}
}

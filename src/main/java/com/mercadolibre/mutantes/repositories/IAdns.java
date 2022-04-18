package com.mercadolibre.mutantes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolibre.mutantes.models.AdnsDTO;

@Repository
public interface IAdns extends MongoRepository<AdnsDTO, String> {

	Optional<AdnsDTO> findByAdn(String[] adn);

	@Query("{mutant : ?0}")
	List<AdnsDTO> countByMutants(boolean isMutant);
}

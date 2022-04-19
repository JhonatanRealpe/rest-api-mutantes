package com.mercadolibre.mutantes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolibre.mutantes.models.AdnsDTO;

@Repository
public interface IAdnsRepository extends MongoRepository<AdnsDTO, String> {

	Optional<AdnsDTO> findByAdn(String[] adn);

	@Query(value ="{mutant: true}", count=true)  
	Integer countByMutants();
	
	@Query(value ="{mutant: false}", count=true)  
	Integer countByHumans();
}

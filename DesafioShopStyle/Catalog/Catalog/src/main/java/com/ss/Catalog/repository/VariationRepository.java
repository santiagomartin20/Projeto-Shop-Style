package com.ss.Catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ss.Catalog.model.Variations;

@Repository
public interface VariationRepository extends MongoRepository<Variations , Long> {


}

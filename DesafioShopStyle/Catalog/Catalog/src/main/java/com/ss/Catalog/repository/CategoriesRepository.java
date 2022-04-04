package com.ss.Catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ss.Catalog.model.Categories;


@Repository
public interface CategoriesRepository  extends MongoRepository<Categories, Long> {

	
	



}

package com.ss.Catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ss.Catalog.model.Products;

@Repository
public interface ProductRepository extends MongoRepository<Products, Long> {

}

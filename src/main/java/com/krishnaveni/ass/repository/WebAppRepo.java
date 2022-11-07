package com.krishnaveni.ass.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.krishnaveni.ass.model.WebApplications;

@Repository
public interface WebAppRepo extends MongoRepository<WebApplications, Integer> {

	@Query("{appName:'?0'}")
    List<WebApplications> findAppsByName(String appName);
	
}

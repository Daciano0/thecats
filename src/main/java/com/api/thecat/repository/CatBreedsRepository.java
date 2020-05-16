package com.api.thecat.repository;

import com.api.thecat.domain.CatBreeds;
import org.springframework.data.repository.CrudRepository;

public interface CatBreedsRepository extends CrudRepository<CatBreeds, String> {

    boolean existsByName(String name);
}

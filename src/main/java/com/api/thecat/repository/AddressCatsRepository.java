package com.api.thecat.repository;

import com.api.thecat.domain.AddressCats;
import org.springframework.data.repository.CrudRepository;

public interface AddressCatsRepository extends CrudRepository<AddressCats, String> {

    AddressCats findByName(String name);
}

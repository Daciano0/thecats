package com.api.thecat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@Document(collection = "catBreeds")
public class CatBreeds implements Serializable {

    @JsonIgnore
    private String id = UUID.randomUUID().toString();

    private String origin;
    private String temperament;
    private String name;
}

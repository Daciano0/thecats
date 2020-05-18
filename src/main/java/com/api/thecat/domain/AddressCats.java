package com.api.thecat.domain;

import com.api.thecat.DTO.BodyResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "AddressCatBreeds")
public class AddressCats implements Serializable {

    @JsonIgnore
    private String id = UUID.randomUUID().toString();

    private String name;
    private List<BodyResponseDTO> bodyResponse = new ArrayList<>();

    @CreatedDate
    private Date createAt;
}

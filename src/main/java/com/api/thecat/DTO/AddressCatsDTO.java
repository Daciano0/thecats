package com.api.thecat.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddressCatsDTO {

    private String name;
    private List<BodyResponseDTO> url = new ArrayList<>();

}

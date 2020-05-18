package com.api.thecat.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BodyResponseDTO {

    private String category;
    private int categoryId;
    private String url;
    private List<CategoriesDTO> categories;

}

package com.api.thecat.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponseDTO
{
    public ErrorResponseDTO(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }


    private String message;


    private List<String> details;


}
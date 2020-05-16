package com.api.thecat.controller;

import com.api.thecat.service.CatBreedsService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@AllArgsConstructor
@Controller
public class CatBreedsController {

    private CatBreedsService catBreedsService;

    @PostMapping(value = "breeds/save")
    public ResponseEntity<?> catBreeds(){

        catBreedsService.generatorCatBreeds();

        return ResponseEntity.status(HttpStatus.CREATED).body("Generated new list of CatBreeds in mongodb!!");
    }

    @PostMapping(value = "address/breeds/save")
    public ResponseEntity<?> addressBreeds(){

        catBreedsService.generatorAddressBreeds();

        return ResponseEntity.status(HttpStatus.CREATED).body("Generated new list of addressCatBreeds in mongodb!!");
    }





}

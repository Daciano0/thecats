package com.api.thecat.controller;

import com.api.thecat.DTO.BodyRequestDTO;
import com.api.thecat.service.AddressCatsService;
import com.api.thecat.service.CatBreedsService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@AllArgsConstructor
@Controller
public class CatBreedsController  {

    private CatBreedsService catBreedsService;

    private AddressCatsService addressCatsService;


    @PostMapping(value = "cats/save")
    public ResponseEntity<?> catBreeds(){

        catBreedsService.generatorCatBreeds();

        return ResponseEntity.status(HttpStatus.CREATED).body("Generated new list of CatBreeds in mongodb!!");
    }

    @PostMapping(value = "address/cats/save")
    public ResponseEntity<?> addressCats(@RequestBody BodyRequestDTO request){

      String result = addressCatsService.generatorAddressCats(request);
      
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }





}

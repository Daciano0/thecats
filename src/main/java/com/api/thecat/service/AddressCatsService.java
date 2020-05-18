package com.api.thecat.service;

import com.api.thecat.DTO.AddressCatsDTO;
import com.api.thecat.DTO.BodyRequestDTO;
import com.api.thecat.DTO.BodyResponseDTO;
import com.api.thecat.DTO.EventLogDTO;
import com.api.thecat.client.CatBreedsClient;
import com.api.thecat.domain.AddressCats;
import com.api.thecat.domain.CatBreeds;
import com.api.thecat.enums.EventsEnum;
import com.api.thecat.exceptions.TheCatException;
import com.api.thecat.repository.AddressCatsRepository;
import com.api.thecat.repository.CatBreedsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@AllArgsConstructor
@Service
public class AddressCatsService {

    private CatBreedsClient catBreedsClient;

    private CatBreedsRepository catBreedsRepository;

    private AddressCatsRepository addressCatsRepository;


    public String generatorAddressCats(BodyRequestDTO request) {

        log.info(new EventLogDTO(EventsEnum.RECEIVED, request.toString()).toString());

        List<CatBreeds> catBreedsList = (List<CatBreeds>) catBreedsRepository.findAll();

        if(catBreedsList.size() < 1){
            throw new TheCatException("please, run the endpoint cats/save first.");
        }

        List<AddressCats> addressCats = new ArrayList<>();

        catBreedsList.forEach(catBreeds -> {

            AddressCatsDTO addressCatsDTO = new AddressCatsDTO();
            addressCatsDTO.setName(catBreeds.getName());

            for(int category : request.getCategories()){
                List<BodyResponseDTO>  bodyResponseDTOS = catBreedsClient.getAddressCats(catBreeds.getName(),category);

                bodyResponseDTOS.forEach(bodyResponseDTO -> { addressCatsDTO.getUrl().add(bodyResponseDTO);});
            }

            AddressCats cats = new AddressCats();

            cats.setName(addressCatsDTO.getName());
            cats.setBodyResponse(addressCatsDTO.getUrl());
            addressCats.add(cats);
        });

        addressCats.forEach(cat -> {
            AddressCats catId =  addressCatsRepository.findByName(cat.getName());
            if(catId != null){
                cat.setId(catId.getId());
            }
        });

            log.info(new EventLogDTO(EventsEnum.CREATED, addressCats.toString()).toString());
            addressCatsRepository.saveAll(addressCats);

     return "Generated";
    }

}

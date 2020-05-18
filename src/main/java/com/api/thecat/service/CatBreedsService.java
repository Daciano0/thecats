package com.api.thecat.service;

import com.api.thecat.DTO.CatBreedsDTO;
import com.api.thecat.DTO.EventLogDTO;
import com.api.thecat.client.CatBreedsClient;
import com.api.thecat.domain.CatBreeds;
import com.api.thecat.enums.EventsEnum;
import com.api.thecat.repository.CatBreedsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@AllArgsConstructor
@Service
public class CatBreedsService {

    private CatBreedsClient catBreedsClient;

    private CatBreedsRepository catBreedsRepository;

    public void generatorCatBreeds() {

        List<CatBreedsDTO>  catBreedsDTO = catBreedsClient.getCatBreeds();

        List<CatBreeds> catBreeds = new ArrayList<>();
            catBreedsDTO.forEach(catDTO -> {
                CatBreeds cat = new CatBreeds();
                cat.setName(catDTO.getName());
                cat.setOrigin(catDTO.getOrigin());
                cat.setTemperament(catDTO.getTemperament());

                if(!catBreedsRepository.existsByName(cat.getName())){
                    catBreeds.add(cat);
                }
            });

        if(catBreeds.size() > 0){
            log.info(new EventLogDTO(EventsEnum.CREATED, catBreeds.toString()).toString());
            catBreedsRepository.saveAll(catBreeds);
        }
    }


}

package com.api.thecat.client;

import com.api.thecat.DTO.BodyResponseDTO;
import com.api.thecat.DTO.CatBreedsDTO;
import com.api.thecat.DTO.CategoriesDTO;
import com.api.thecat.DTO.EventLogDTO;
import com.api.thecat.config.CatBreedsConfig;
import com.api.thecat.enums.EventsEnum;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Log4j2
@AllArgsConstructor
@Component
public class CatBreedsClient {

    private CatBreedsConfig catBreedsConfig;

    private  RestTemplate restTemplate;

    private HttpEntity httpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new  HttpEntity<>(headers);
    }

    public List<CatBreedsDTO> getCatBreeds(){

        log.info(new EventLogDTO(EventsEnum.REQUEST, Map.of("URL", catBreedsConfig.getUrl_cat())).toString());

        ResponseEntity<List<CatBreedsDTO>> response = restTemplate.exchange(
                catBreedsConfig.getUrl_cat(),
                HttpMethod.GET, httpEntity(),
                new ParameterizedTypeReference<List<CatBreedsDTO>>() {});

        log.info(new EventLogDTO(EventsEnum.RESPONSE, response.getBody()).toString());

        return response.getBody();
    }


    public List<BodyResponseDTO> getAddressCats(String name, int category) {

        log.info(new EventLogDTO(EventsEnum.REQUEST, Map.of("Url",catBreedsConfig.getUrl_address()+"name="+name+"&category_ids="+category)).toString());

        CategoriesDTO categoriesDTO = new CategoriesDTO();

            ResponseEntity<List<BodyResponseDTO>> response = restTemplate.exchange(
                    catBreedsConfig.getUrl_address()+"?name="+name+"&category_ids="+category+"&limit=3",
                    HttpMethod.GET, httpEntity(),
                    new ParameterizedTypeReference<List<BodyResponseDTO>>() {});

        response.getBody().forEach(bodyResponseDTO -> {
            bodyResponseDTO.setCategoryId(category);
            bodyResponseDTO.setCategory(bodyResponseDTO.getCategories().get(0).getName());
            bodyResponseDTO.setCategories(null);
        });

        log.info(new EventLogDTO(EventsEnum.RESPONSE, response.getBody().toString()).toString());

        return response.getBody();
    }


}

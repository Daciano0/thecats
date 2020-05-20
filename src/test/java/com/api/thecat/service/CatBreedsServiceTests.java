package com.api.thecat.service;

import com.api.thecat.DTO.CatBreedsDTO;
import com.api.thecat.client.CatBreedsClient;
import com.api.thecat.domain.CatBreeds;
import com.api.thecat.repository.CatBreedsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest
@ActiveProfiles("test")
public class CatBreedsServiceTests {

    @Autowired
    private CatBreedsService catBreedsService;

    @MockBean
    private CatBreedsClient catBreedsClient;

    @MockBean
    private CatBreedsRepository catBreedsRepository;

    private CatBreedsDTO catBreedsDTO;

    private CatBreeds catBreeds;


    @Test
    public void test_generator_cats1(){

        List<CatBreedsDTO> catBreedsDTO = new ArrayList<>();

        this.catBreedsDTO = new CatBreedsDTO();

        this.catBreedsDTO.setName("test");
        this.catBreedsDTO.setOrigin("test");
        this.catBreedsDTO.setTemperament("test");

        catBreedsDTO.add(this.catBreedsDTO);

        Mockito.when(catBreedsClient.getCatBreeds()).thenReturn(catBreedsDTO);

        Mockito.when(catBreedsRepository.existsByName(anyString())).thenReturn(Boolean.TRUE);

        catBreedsService.generatorCatBreeds();
    }

    @Test
    public void test_generator_cats2(){

        List<CatBreeds> catBreeds = new ArrayList<>();

        List<CatBreedsDTO> catBreedsDTO = new ArrayList<>();

        this.catBreeds = new CatBreeds();
        this.catBreeds.setName("test");

        this.catBreedsDTO = new CatBreedsDTO();

        this.catBreedsDTO.setName("test");
        this.catBreedsDTO.setOrigin("test");
        this.catBreedsDTO.setTemperament("test");

        catBreedsDTO.add(this.catBreedsDTO);

        Mockito.when(catBreedsClient.getCatBreeds()).thenReturn(catBreedsDTO);

        Mockito.when(catBreedsRepository.existsByName(this.catBreeds.getName())).thenReturn(Boolean.FALSE);

        Mockito.when(catBreedsRepository.saveAll(catBreeds)).thenReturn(catBreeds);

        catBreedsService.generatorCatBreeds();
    }
}

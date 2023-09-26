package project.music.spring.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.music.spring.mapper.CountryMapper;
import project.music.spring.model.dto.CountryDTO;
import project.music.spring.model.entity.Country;
import project.music.spring.service.CountryService;

import java.util.List;
import java.util.UUID;


import static project.music.spring.constant.Constants.COUNTRIES;
@RestController
@RequestMapping(value = COUNTRIES)
public class CountryController {

    private final CountryService countryService;

    private final CountryMapper countryMapper;

    public CountryController(CountryService countryService, CountryMapper countryMapper) {
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getCountries(){
        try{
            List<Country> countries = countryService.getCountry();
            List<CountryDTO> countryDTOS = countries.stream()
                    .map(countryMapper::entityToDto).toList();
            return new ResponseEntity<>(countryDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryDTO> createCountry(@Valid @RequestBody CountryDTO countryDTO){
        try {
            Country newCountry = countryService.add(countryDTO.name(), countryDTO.capital(), countryDTO.phoneCode(), countryDTO.zipCode());
            CountryDTO newCountryDTO = countryMapper.entityToDto(newCountry);
            return new ResponseEntity<>(newCountryDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO, @PathVariable UUID id){
        Country countryToUpdate = countryMapper.dtoToEntity(countryDTO);
        Country updatedCountry = countryService.updateById(countryToUpdate, id);
        CountryDTO updatedCountryDTO = countryMapper.entityToDto(updatedCountry);
        return new ResponseEntity<>(updatedCountryDTO, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<CountryDTO> deleteCountry(@PathVariable UUID id){
        try {
            countryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}

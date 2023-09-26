package project.music.spring.service;

import project.music.spring.model.entity.Country;

import java.util.List;
import java.util.UUID;

public interface CountryService {

    List<Country> getCountry();

    Country add(String name, String capital, Integer phoneCode, Integer zipCode);

    Country updateById(Country country, UUID id);

    void deleteById(UUID id);
}

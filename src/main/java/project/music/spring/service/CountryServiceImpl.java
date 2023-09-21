package project.music.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.music.spring.exceptions.CountryNotRegistered;
import project.music.spring.exceptions.SongNotRegistered;
import project.music.spring.model.entity.Country;
import project.music.spring.model.entity.Song;
import project.music.spring.repository.CountryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService{

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountry(){
        List<Country> country = new ArrayList<>();
        this.countryRepository.findAll().forEach(country::add);
        return country;
    }

    public Country add(String name, String capital, Integer phoneCode, Integer zipCode){
        Country country = new Country(name, capital, phoneCode, zipCode);
        return this.countryRepository.save(country);
    }

    public Country updateById(Country country, UUID id){
        Optional<Country> optionalCountry = this.countryRepository.findById(id);
        if (optionalCountry.isEmpty()){
            throw new CountryNotRegistered("No country found with the given ID: " + id);
        }else{
            log.info("Updatting country with name {}", country.getName());
            Country oldCountry = optionalCountry.get();
            oldCountry.setName(country.getName());
            oldCountry.setCapital(country.getCapital());
            oldCountry.setPhoneCode(country.getPhoneCode());
            oldCountry.setZipCode(country.getZipCode());
            oldCountry = this.countryRepository.save(oldCountry);
            return oldCountry;
        }
    }

    public void deleteById(UUID id){
        this.countryRepository.deleteById(id);
    }



}

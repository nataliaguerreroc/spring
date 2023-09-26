package project.music.spring.repository;

import org.springframework.data.repository.CrudRepository;
import project.music.spring.model.entity.Country;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CountryRepository extends CrudRepository<Country, Long> {

    List<Country> findByName(String name);

    Optional<Country> findById(UUID id);

    void deleteById(UUID id);
}

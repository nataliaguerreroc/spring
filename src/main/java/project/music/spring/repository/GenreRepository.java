package project.music.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import project.music.spring.model.entity.Genre;
import project.music.spring.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    @Query("SELECT name from Genre")
    List<String> getNames();

    List<Genre> findAll();

    Optional<Genre> findById(UUID id);

    void deleteById(UUID id);


}

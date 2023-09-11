package project.music.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import project.music.spring.model.entity.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    @Query("SELECT name from Genre")
    List<String> getNames();

    List<Genre> findAll();


}

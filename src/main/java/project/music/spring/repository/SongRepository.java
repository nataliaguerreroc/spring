package project.music.spring.repository;

import org.springframework.data.repository.CrudRepository;
import project.music.spring.model.entity.Song;
import project.music.spring.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SongRepository extends CrudRepository<Song, Long> {

    List<Song> findByName(String name);

    Optional<Song> findById(UUID id);

    void deleteById(UUID id);
}

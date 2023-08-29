package project.music.spring.repository;

import org.springframework.data.repository.CrudRepository;
import project.music.spring.model.entity.Song;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Long> {

    List<Song> findByName(String name);
}

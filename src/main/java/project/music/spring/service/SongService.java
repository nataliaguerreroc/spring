package project.music.spring.service;

import project.music.spring.model.entity.Song;

import java.util.List;
import java.util.UUID;

public interface SongService {
    List<Song> getSongs();

    Song add(String name, Integer duration);

    void deleteById(UUID id);

    Song updateById(Song song,UUID id);

}

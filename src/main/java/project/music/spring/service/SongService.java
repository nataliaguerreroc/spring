package project.music.spring.service;

import project.music.spring.model.entity.Song;

import java.util.List;

public interface SongService {
    List<Song> getSongs();

    Song add(String name, Integer duration);

    void deleteById(Long id);

    Song updateById(Song song,Long id);

}

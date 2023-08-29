package project.music.spring.service;

import project.music.spring.model.entity.Song;

import java.util.List;
import java.util.Map;

public interface SongService {

    List<Song> getSongs();

    List <Map<String,String>> getSongsInfo();

    Song add(String name);

    void deleteById(Long id);

    Song updateById(Song song,Long id);

}

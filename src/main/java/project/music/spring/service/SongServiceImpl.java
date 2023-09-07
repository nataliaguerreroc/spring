package project.music.spring.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import project.music.spring.model.entity.Song;
import project.music.spring.repository.SongRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService{

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getSongs(){
        List<Song> songs = new ArrayList<>();
        this.songRepository.findAll().forEach(songs::add);
        return songs;
    }

    public Song add(String name, Integer duration){
        Song song = new Song(name, duration);
        return this.songRepository.save(song);
    }

    public Song updateById(Song song,Long id){
        Optional<Song> optionalSong = this.songRepository.findById(id);
        Song oldSong = null;
        if(optionalSong.isPresent()){
            oldSong = optionalSong.get();
            oldSong.setName(song.getName());
            oldSong.setDuration(song.getDuration());
            oldSong = this.songRepository.save(oldSong);
        }
        return oldSong;
    }

    public void deleteById(Long id){
        Optional<Song> optionalSong = this.songRepository.findById(id);
        if(optionalSong.isPresent()){
            Song song = optionalSong.get();
            song.getUsers().stream().forEach(u -> u.getSongs().remove(song));
            this.songRepository.deleteById(id);
        }
    }


}

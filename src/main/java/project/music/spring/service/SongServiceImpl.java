package project.music.spring.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.music.spring.exceptions.SongNotRegistered;
import project.music.spring.model.entity.Song;
import project.music.spring.repository.SongRepository;
import project.music.spring.repository.repositoryQDSL.SongRepositoryQDSL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class SongServiceImpl implements SongService{

    private final SongRepository songRepository;

    private final SongRepositoryQDSL songRepositoryQDSL;


    public SongServiceImpl(SongRepository songRepository, SongRepositoryQDSL songRepositoryQDSL) {
        this.songRepository = songRepository;
        this.songRepositoryQDSL = songRepositoryQDSL;
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

    public List<Song> findSongByDuration(){
        return songRepositoryQDSL.findSongByDuration();
    }

    public Song updateById(Song song, UUID id){
        Optional<Song> optionalSong = this.songRepository.findById(id);
        if (optionalSong.isEmpty()){
            throw new SongNotRegistered("No song found with the given ID: " + id);
        }else{
            log.info("Updatting song with name {}", song.getName());
            Song oldSong = optionalSong.get();
            oldSong.setName(song.getName());
            oldSong.setDuration(song.getDuration());
            oldSong = this.songRepository.save(oldSong);
            return oldSong;
        }
    }

    public void deleteById(UUID id){
        Optional<Song> optionalSong = this.songRepository.findById(id);
        if(optionalSong.isPresent()){
            Song song = optionalSong.get();
            song.getUsers().stream().forEach(u -> u.getSongs().remove(song));
            this.songRepository.deleteById(id);
        }
    }


}

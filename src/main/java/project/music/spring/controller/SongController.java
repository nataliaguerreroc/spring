package project.music.spring.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.music.spring.mapper.UserMapper;
import project.music.spring.model.dto.SongDTO;
import project.music.spring.model.dto.UserDTO;
import project.music.spring.model.entity.Song;
import project.music.spring.mapper.SongMapper;
import project.music.spring.model.entity.User;
import project.music.spring.service.SongService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;


    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SongDTO> createSong(@Valid @RequestBody SongDTO songDTO){
        try{
            Song newSong = songService.add(songDTO.name());
            SongDTO newSongDTO = SongMapper.INSTANCE.entityToDto(newSong);
            return new ResponseEntity<>(newSongDTO, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<SongDTO>> getSong(){
        try{
            List<Song> songs = songService.getSongs();
            List<SongDTO> songDTOS = songs.stream()
                    .map(SongMapper.INSTANCE::entityToDto).toList();
            return new ResponseEntity<>(songDTOS, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<SongDTO> updateSong(@RequestBody SongDTO songDTO, @PathVariable Long id){
        try{
            Song songToUpdate = SongMapper.INSTANCE.dtoToEntity(songDTO);
            Song updatedSong = songService.updateById(songToUpdate, id);
            SongDTO updatedSongDTO = SongMapper.INSTANCE.entityToDto(updatedSong);
            return new ResponseEntity<>(updatedSongDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SongDTO> deleteUser(@PathVariable Long id){
        try{
            songService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}

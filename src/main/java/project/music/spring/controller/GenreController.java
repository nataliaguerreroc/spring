package project.music.spring.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.music.spring.mapper.GenreMapper;
import project.music.spring.model.dto.GenreDTO;
import project.music.spring.model.dto.SongDTO;
import project.music.spring.model.dto.UserDTO;
import project.music.spring.model.entity.Genre;
import project.music.spring.model.entity.Song;
import project.music.spring.model.entity.User;
import project.music.spring.service.GenreService;

import java.util.List;

import static project.music.spring.constant.Constants.GENRES;

@RestController
@RequestMapping(value = GENRES)
public class GenreController {

    private final GenreService genreService;

    private final GenreMapper genreMapper;


    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getGenres(){
        try{
            List<Genre> genres = genreService.getGenres();
            List<GenreDTO> genreDTOS = genres.stream()
                    .map(genreMapper::entityToDto).toList();
            return new ResponseEntity<>(genreDTOS, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO){
        try{
            Genre newGenre = genreService.add(genreDTO.name());
            GenreDTO newGenreDTO = genreMapper.entityToDto(newGenre);
            return new ResponseEntity<>(newGenreDTO, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenreDTO> updateGenre(@RequestBody GenreDTO genreDTO, @PathVariable Long id){
        try{
            Genre genreToUpdate = genreMapper.dtoToEntity(genreDTO);
            Genre updatedGenre = genreService.updateById(genreToUpdate, id);
            GenreDTO updatedGenreDTO = genreMapper.entityToDto(updatedGenre);
            return new ResponseEntity<>(updatedGenreDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GenreDTO> deleteUser(@PathVariable Long id){
        try{
            genreService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}

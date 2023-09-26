package project.music.spring.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.music.spring.mapper.GenreMapper;
import project.music.spring.model.dto.GenreDTO;
import project.music.spring.model.entity.Genre;
import project.music.spring.service.GenreService;

import java.util.List;
import java.util.UUID;

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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenreDTO> updateGenre(@RequestBody GenreDTO genreDTO, @PathVariable UUID id){
            Genre genreToUpdate = genreMapper.dtoToEntity(genreDTO);
            Genre updatedGenre = genreService.updateById(genreToUpdate, id);
            GenreDTO updatedGenreDTO = genreMapper.entityToDto(updatedGenre);
            return new ResponseEntity<>(updatedGenreDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<GenreDTO> deleteUser(@PathVariable UUID id){
        try{
            genreService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}

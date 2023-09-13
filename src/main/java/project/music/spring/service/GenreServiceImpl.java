package project.music.spring.service;

import org.springframework.stereotype.Service;
import project.music.spring.model.entity.Genre;
import project.music.spring.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getGenres(){
        return genreRepository.findAll();
    }

    public Genre add(String name){
        Genre genre = new Genre(name);
        genre = this.genreRepository.save(genre);
        return genre;

    }

    public Genre updateById(Genre genre, UUID id){
        Optional<Genre> optionalGenre = this.genreRepository.findById(id);
        Genre oldGenre = null;
        if(optionalGenre.isPresent()){
            oldGenre = optionalGenre.get();
            oldGenre.setName(genre.getName());
            oldGenre = this.genreRepository.save(oldGenre);
        }
        return oldGenre;
    }

    public void deleteById(UUID id){
        this.genreRepository.deleteById(id);
    }


}

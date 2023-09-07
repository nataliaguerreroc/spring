package project.music.spring.service;

import project.music.spring.model.entity.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getGenres();

    Genre add(String name);

    void deleteById(Long id);

    Genre updateById(Genre genre, Long id);
}

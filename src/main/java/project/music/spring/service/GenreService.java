package project.music.spring.service;

import project.music.spring.model.entity.Genre;

import java.util.List;
import java.util.UUID;

public interface GenreService {

    List<Genre> getGenres();

    Genre add(String name);

    void deleteById(UUID id);

    Genre updateById(Genre genre, UUID id);
}

package project.music.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.music.spring.model.dto.GenreDTO;
import project.music.spring.model.entity.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "id", ignore = true)
    Genre dtoToEntity(GenreDTO dto);

    GenreDTO entityToDto(Genre entity);



}

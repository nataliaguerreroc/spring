package project.music.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.music.spring.model.dto.SongDTO;
import project.music.spring.model.entity.Song;


@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    Song dtoToEntity(SongDTO dto);

    SongDTO entityToDto(Song entity);

}
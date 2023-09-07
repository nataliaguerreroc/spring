package project.music.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import project.music.spring.model.dto.SongDTO;
import project.music.spring.model.dto.UserDTO;
import project.music.spring.model.entity.Song;
import project.music.spring.model.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    Song dtoToEntity(SongDTO dto);

    SongDTO entityToDto(Song entity);

}
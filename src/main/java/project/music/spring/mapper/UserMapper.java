package project.music.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import project.music.spring.model.dto.UserDTO;
import project.music.spring.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    User dtoToEntity(UserDTO dto);

    UserDTO entityToDto(User entity);

}

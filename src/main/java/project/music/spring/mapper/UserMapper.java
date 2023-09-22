package project.music.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.music.spring.model.dto.UserDTO;
import project.music.spring.model.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    User dtoToEntity(UserDTO dto);

    UserDTO entityToDto(User entity);

    List<UserDTO> entityListToListDTO(List<User> entityList);

    List<User> entityListDTOToList(List<UserDTO> userDTOList);
}

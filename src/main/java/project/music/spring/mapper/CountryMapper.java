package project.music.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.music.spring.model.dto.CountryDTO;
import project.music.spring.model.entity.Country;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country dtoToEntity(CountryDTO dto);

    CountryDTO entityToDto(Country entity);
}

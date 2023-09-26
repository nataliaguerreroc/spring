package project.music.spring.model.dto;

import java.io.Serializable;

public record CountryDTO(String name, String capital, Integer phoneCode, Integer zipCode) implements Serializable {
}

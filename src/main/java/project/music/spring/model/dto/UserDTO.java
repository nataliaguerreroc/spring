package project.music.spring.model.dto;

import java.io.Serializable;

public record UserDTO(String name, String email, String password, String birthdate) implements Serializable {

}

package project.music.spring.model.dto;

import java.io.Serializable;

public record SongDTO(String name, Integer duration) implements Serializable {
}

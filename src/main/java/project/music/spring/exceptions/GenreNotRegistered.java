package project.music.spring.exceptions;

public class GenreNotRegistered extends RuntimeException{

    public GenreNotRegistered(String message) {
        super(message);
    }
}
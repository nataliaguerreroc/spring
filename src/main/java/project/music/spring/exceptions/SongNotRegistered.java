package project.music.spring.exceptions;

public class SongNotRegistered extends RuntimeException{
    public SongNotRegistered(String message) {
        super(message);
    }
}

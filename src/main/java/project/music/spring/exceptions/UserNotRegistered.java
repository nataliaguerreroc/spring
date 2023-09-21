package project.music.spring.exceptions;

public class UserNotRegistered extends RuntimeException {

    public UserNotRegistered(String message) {
        super(message);
    }
}

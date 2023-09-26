package project.music.spring.exceptions;

public class CountryNotRegistered extends RuntimeException{

    public CountryNotRegistered(String message){
        super(message);
    }
}

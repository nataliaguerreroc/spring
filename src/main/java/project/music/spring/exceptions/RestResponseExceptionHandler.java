package project.music.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "project.music.spring.controller")
public class RestResponseExceptionHandler {

    @ExceptionHandler(value = {GenreNotRegistered.class})
    public ResponseEntity<String> genreNotFoundException(GenreNotRegistered genreNotRegistered) {
        return new ResponseEntity<>(genreNotRegistered.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserNotRegistered.class})
    public ResponseEntity<String> userNotFoundException(UserNotRegistered userNotRegistered) {
        return new ResponseEntity<>(userNotRegistered.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {SongNotRegistered.class})
    public ResponseEntity<String> songNotFoundException(SongNotRegistered songNotRegistered) {
        return new ResponseEntity<>(songNotRegistered.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CountryNotRegistered.class})
    public ResponseEntity<String> countryNotFoundException(CountryNotRegistered countryNotRegistered) {
        return new ResponseEntity<>(countryNotRegistered.getMessage(), HttpStatus.NOT_FOUND);
    }




}

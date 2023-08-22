package project.music.spring.model.entity;


import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table (name = "userC")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "email", unique = true, nullable = false)
    private String email;

    @Column (name = "password", nullable = false)
    private String password;

    @Column (name = "birthdate", nullable = false)
    private String birthdate;

    public User(String name, String email, String password, String birthdate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }

    public User(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}

package project.music.spring.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Data
@Getter
@Setter
@Entity
@Table(name = "userC")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column (name = "email", unique = true, nullable = false)
    private String email;

    @Column (name = "password", nullable = false)
    private String password;

    @Column (name = "birthdate", nullable = false)
    private String birthdate;

    @ManyToMany(mappedBy = "users")
    private Set<Song> songs = new HashSet<>();

    public User(String name, String email, String password, String birthdate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }

    public User(){

    }

}

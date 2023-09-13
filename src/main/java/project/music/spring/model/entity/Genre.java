package project.music.spring.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Table(name = "genre")
public class Genre extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "genre_id")
    private Set<Song> songs = new HashSet<>();

    public Genre(String name) {
        this.name = name;
    }

    public Genre(){

    }
}

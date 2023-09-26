package project.music.spring.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "Country")
public class Country extends BaseEntity{

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "capital", unique = true, nullable = false)
    private String capital;

    @Column(name = "phoneCode", unique = true, nullable = false)
    private Integer phoneCode;

    @Column(name = "zipCode", unique = true, nullable = false)
    private Integer zipCode;

    public Country(String name, String capital, Integer phoneCode, Integer zipCode) {
        this.name = name;
        this.capital = capital;
        this.phoneCode = phoneCode;
        this.zipCode = zipCode;
    }

    public Country(){

    }
}

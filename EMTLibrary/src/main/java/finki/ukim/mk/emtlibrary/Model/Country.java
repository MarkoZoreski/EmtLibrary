package finki.ukim.mk.emtlibrary.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contintent;

    public Country() {
    }

    public Country(String name, String contintent) {
        this.name = name;
        this.contintent = contintent;
    }
}

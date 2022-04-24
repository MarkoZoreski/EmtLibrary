package finki.ukim.mk.emtlibrary.dto;

import finki.ukim.mk.emtlibrary.Model.CategoryType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class BookDto {
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryType category;
    private Long authorId;
    private int availableCopies;

    public BookDto() {
    }

    public BookDto(String name, CategoryType category, Long authorId, int availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}

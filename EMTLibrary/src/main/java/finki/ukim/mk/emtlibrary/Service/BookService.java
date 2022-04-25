package finki.ukim.mk.emtlibrary.Service;

import finki.ukim.mk.emtlibrary.Model.Author;
import finki.ukim.mk.emtlibrary.Model.Book;
import finki.ukim.mk.emtlibrary.Model.CategoryType;
import finki.ukim.mk.emtlibrary.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, CategoryType category, Long authorid, int availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, CategoryType category, Long authorid, int availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    Optional<Book> markAsTaken(Long id);
}

package finki.ukim.mk.emtlibrary.Service.impl;

import finki.ukim.mk.emtlibrary.Model.Author;
import finki.ukim.mk.emtlibrary.Model.Book;
import finki.ukim.mk.emtlibrary.Model.CategoryType;
import finki.ukim.mk.emtlibrary.Model.exceptions.AuthorNotFoundException;
import finki.ukim.mk.emtlibrary.Model.exceptions.BookNotFoundException;
import finki.ukim.mk.emtlibrary.Repository.AuthorRepository;
import finki.ukim.mk.emtlibrary.Repository.BookRepository;
import finki.ukim.mk.emtlibrary.Service.BookService;
import finki.ukim.mk.emtlibrary.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, CategoryType category, Long authorid, int availableCopies) {
        Author author = this.authorRepository.findById(authorid)
                .orElseThrow(AuthorNotFoundException::new);

        return Optional.of(this.bookRepository.save(new Book(name,category,author,availableCopies)));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(AuthorNotFoundException::new);
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies())));
    }

    @Override
    public Optional<Book> edit(Long id, String name, CategoryType category, Long authorid, int availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(BookNotFoundException::new);

        book.setName(name);
        book.setCategory(category);

        Author author = this.authorRepository.findById(authorid)
                .orElseThrow(AuthorNotFoundException::new);
        book.setAuthor(author);

        book.setAvailableCopies(availableCopies);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(BookNotFoundException::new);

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());

        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(AuthorNotFoundException::new);
        book.setAuthor(author);

        book.setAvailableCopies(book.getAvailableCopies());

        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = findById(id).orElseThrow(BookNotFoundException::new);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        return Optional.of(bookRepository.save(book));
    }
}

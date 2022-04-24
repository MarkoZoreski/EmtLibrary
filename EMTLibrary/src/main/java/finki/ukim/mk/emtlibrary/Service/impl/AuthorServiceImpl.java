package finki.ukim.mk.emtlibrary.Service.impl;

import finki.ukim.mk.emtlibrary.Model.Author;
import finki.ukim.mk.emtlibrary.Model.exceptions.AuthorNotFoundException;
import finki.ukim.mk.emtlibrary.Repository.AuthorRepository;
import finki.ukim.mk.emtlibrary.Service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findbyId(Long id) {
        return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }
}

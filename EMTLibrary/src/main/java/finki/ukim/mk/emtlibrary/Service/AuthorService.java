package finki.ukim.mk.emtlibrary.Service;

import finki.ukim.mk.emtlibrary.Model.Author;

import java.util.List;

public interface AuthorService {
    Author findbyId(Long id);
    List<Author> listAll();
}

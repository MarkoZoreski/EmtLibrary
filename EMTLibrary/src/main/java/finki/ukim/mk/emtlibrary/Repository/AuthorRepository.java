package finki.ukim.mk.emtlibrary.Repository;

import finki.ukim.mk.emtlibrary.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}

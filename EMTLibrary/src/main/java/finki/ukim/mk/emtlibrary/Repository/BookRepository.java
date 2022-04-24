package finki.ukim.mk.emtlibrary.Repository;

import finki.ukim.mk.emtlibrary.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}

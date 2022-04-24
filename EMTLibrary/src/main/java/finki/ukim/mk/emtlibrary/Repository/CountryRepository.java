package finki.ukim.mk.emtlibrary.Repository;

import finki.ukim.mk.emtlibrary.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}

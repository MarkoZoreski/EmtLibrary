package finki.ukim.mk.emtlibrary.Service;

import finki.ukim.mk.emtlibrary.Model.Country;

import java.util.List;

public interface CountryService {
    Country findById(Long id);
    List<Country> listAll();
}

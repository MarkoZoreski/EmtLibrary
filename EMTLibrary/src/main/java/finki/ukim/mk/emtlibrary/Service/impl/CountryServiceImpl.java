package finki.ukim.mk.emtlibrary.Service.impl;

import finki.ukim.mk.emtlibrary.Model.Country;
import finki.ukim.mk.emtlibrary.Model.exceptions.CountryNotFoundException;
import finki.ukim.mk.emtlibrary.Repository.CountryRepository;
import finki.ukim.mk.emtlibrary.Service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }
}

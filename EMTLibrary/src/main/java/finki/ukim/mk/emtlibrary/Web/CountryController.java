package finki.ukim.mk.emtlibrary.Web;

import finki.ukim.mk.emtlibrary.Model.Country;
import finki.ukim.mk.emtlibrary.Service.CountryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> lisAll(){
        return this.countryService.listAll();
    }
}

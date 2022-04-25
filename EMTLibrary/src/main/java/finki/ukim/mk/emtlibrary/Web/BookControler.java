package finki.ukim.mk.emtlibrary.Web;

import finki.ukim.mk.emtlibrary.Model.Book;
import finki.ukim.mk.emtlibrary.Model.CategoryType;
import finki.ukim.mk.emtlibrary.Service.BookService;
import finki.ukim.mk.emtlibrary.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookControler {
    private final BookService bookService;

    public BookControler(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDto bookDto) {
        System.out.println(id);
        System.out.println(bookDto);
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/categories")
    private List<String> findAllCategories() {
        return Arrays.stream(CategoryType.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @PutMapping("/markAsTaken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id){
        return bookService.markAsTaken(id)
                .map(book->ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

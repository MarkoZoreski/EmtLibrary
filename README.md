# EmtLibrary

EmtLibrary is a Rest-Api backend aplication for EMTLibraryReact application.

*** Note: both of the apps need to be started at the same time for the Program to work ***
link to EMTLibraryReact: https://github.com/MarkoZoreski/EMTLibraryReactApp

---
## How to start the applications

1. Download both applications(EmtLibrary and EMTLibraryReact)
2. Open EmtLibrary in an IDE and run the app
3. Open EMTLibraryReact in an IDE and use the terminal or navigate to EMTLibraryReact/libraryreactapp/ in command prompt and enter the following command:

### `npm install -g node-modules`

4. After the modules are installed successfully enter the following command:

### `npm start`

5. If all the above steps are done correctly you should be able to view the application in a browser at the following URL: http://localhost:3000/

---

## Main goal of the app

The main goal of the app is for the user to be able to add a new book in a library by specifying the name of the book, category, author and available copies.
The user should then be able to see all the added books listed and click the "Mark as Taken" button to subtract a book form the available copies.
If all the available copies are taken the user won't be able to take another book unless the books are restocked by editing them.
The user can also chose to delete the book, and in doinjg so the book will be removed from the books listed.

---

## Main Code

### The Book model class containing all the variables needed for the app logic
```csharp
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryType category;
    @ManyToOne
    private Author author;
    private int availableCopies;

    public Book(String name, CategoryType category, Author author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
```
### Logic for all the Book methods


```csharp
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
            System.out.println(book);
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());

        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(AuthorNotFoundException::new);
        book.setAuthor(author);

        book.setAvailableCopies(bookDto.getAvailableCopies());

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

```

---

## Can I view how the app looks without downloading it myself? 
Checkout how the app looks in the README file of EMTLibraryReactApp

link to EMTLibraryReact: https://github.com/MarkoZoreski/EMTLibraryReactApp

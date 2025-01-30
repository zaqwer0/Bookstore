package by.example.bookstore_api.controller;

import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.dto.response.BookResponseDto;
import by.example.bookstore_api.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {

    private final BookService bookService;


    @GetMapping("/sorted")
    public ResponseEntity<List<BookResponseDto>> findAllSorted(@RequestParam String sortBy) {
        log.info("Received sortBy parameter: {}", sortBy);
        try {
            List<BookResponseDto> sortedBooks = bookService.findAllSorted(sortBy);
            return ResponseEntity.ok(sortedBooks);
        } catch (IllegalArgumentException e) {
            log.error("Error during sorting: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable("id") UUID bookId) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }
    @GetMapping
    //todo where is pagination?
    public ResponseEntity<List<BookResponseDto>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BookRequestDto bookRequestDto) {
        bookService.save(bookRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") UUID bookId, @RequestBody BookRequestDto bookRequestDto) {
        bookService.update(bookId, bookRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") UUID bookId) {
        bookService.delete(bookId);
        return ResponseEntity.noContent().build();
    }

}

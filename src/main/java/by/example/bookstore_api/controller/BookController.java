package by.example.bookstore_api.controller;

import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.dto.response.BookResponseDto;
import by.example.bookstore_api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    @GetMapping("{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable("id") UUID bookId) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }
    @GetMapping
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

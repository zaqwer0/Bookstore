package by.example.bookstore_api.controller;

import by.example.bookstore_api.model.dto.request.BookstoreRequestDto;
import by.example.bookstore_api.model.dto.response.BookstoreResponseDto;
import by.example.bookstore_api.service.BookstoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("bookstores")
@RequiredArgsConstructor
public class BookstoreController {

    private final BookstoreService bookstoreService;

    @GetMapping("{id}")
    public ResponseEntity<BookstoreResponseDto> findById (@PathVariable("id") UUID bookstoreId) {
        return ResponseEntity.ok(bookstoreService.findById(bookstoreId));
    }

    @GetMapping
    public ResponseEntity<List<BookstoreResponseDto>> findAll () {
        return ResponseEntity.ok(bookstoreService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> save (@RequestBody BookstoreRequestDto bookstoreRequestDto) {
        bookstoreService.save(bookstoreRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") UUID bookstoreId) {
        bookstoreService.delete(bookstoreId);
        return ResponseEntity.noContent().build();
    }
}

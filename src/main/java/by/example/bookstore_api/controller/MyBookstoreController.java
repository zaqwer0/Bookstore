package by.example.bookstore_api.controller;

import by.example.bookstore_api.model.dto.request.MyBookstoreRequestDto;
import by.example.bookstore_api.model.dto.response.MyBookstoreResponseDto;
import by.example.bookstore_api.service.MyBookstoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bookstores")
@RequiredArgsConstructor
@Tag(name = "MyBookstoreController", description = "Book operations in the single bookstore")
public class MyBookstoreController {

  private final MyBookstoreService myBookstoreService;

  @GetMapping("{bookId}")
  public ResponseEntity<MyBookstoreResponseDto> findByBookId(@PathVariable("bookId") UUID bookId) {
    return ResponseEntity.ok(myBookstoreService.findByBookId(bookId));
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody MyBookstoreRequestDto myBookstoreRequestDto) {
    myBookstoreService.save(myBookstoreRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("{bookId}")
  public ResponseEntity<Void> update(
      @PathVariable("bookId") UUID bookId,
      @RequestBody MyBookstoreRequestDto myBookstoreRequestDto) {
    myBookstoreService.update(bookId, myBookstoreRequestDto);
    return ResponseEntity.ok().build();
  }

  @RequestMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") UUID bookstoreId) {
    myBookstoreService.delete(bookstoreId);
    return ResponseEntity.noContent().build();
  }
}

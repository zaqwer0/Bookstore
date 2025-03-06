package by.example.bookstore.api.controller;

import by.example.bookstore.api.model.dto.request.AuthorRequestDto;
import by.example.bookstore.api.model.dto.response.AuthorResponseDto;
import by.example.bookstore.api.service.AuthorService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/authors")
public class AuthorController {

  private final AuthorService authorService;

  @GetMapping("{id}")
  public ResponseEntity<AuthorResponseDto> findById(@PathVariable("id") UUID authorId) {
    AuthorResponseDto authorResponse = authorService.findById(authorId);
    return ResponseEntity.ok(authorResponse);
  }

  @GetMapping
  public ResponseEntity<List<AuthorResponseDto>> findAll(
    @RequestParam(name = "name",required = false) String filter) {
    return ResponseEntity.ok(authorService.findAll(filter));
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody AuthorRequestDto authorRequestDto) {
    authorService.save(authorRequestDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> update(
      @PathVariable("id") UUID authorId, @RequestBody AuthorRequestDto authorRequestDto) {
    authorService.update(authorId, authorRequestDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteById(@PathVariable("id") UUID authorId) {
    authorService.deleteById(authorId);
    return ResponseEntity.noContent().build();
  }

}

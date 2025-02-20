package by.example.bookstore_api.controller;

import by.example.bookstore_api.model.dto.request.UserRequestDto;
import by.example.bookstore_api.model.dto.response.UserResponseDto;
import by.example.bookstore_api.service.UserService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

  private final UserService userService;

  @GetMapping("{id}")
  public ResponseEntity<UserResponseDto> findById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> findAll(
      @RequestParam(value = "filter", required = false) String filter) {
    List<UserResponseDto> users = userService.findAll(filter);
    return ResponseEntity.ok(users);
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody UserRequestDto userRequestDto) {
    userService.save(userRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> update(
      @PathVariable("id") UUID id, @RequestBody UserRequestDto userRequestDto) {
    userService.update(id, userRequestDto);
    return ResponseEntity.ok().build();
  }

  @RequestMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

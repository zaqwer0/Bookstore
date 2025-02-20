package by.example.bookstore_api.service.impl;

import by.example.bookstore_api.exception.AuthorExists;
import by.example.bookstore_api.mapper.AuthorMapper;
import by.example.bookstore_api.model.dto.request.AuthorRequestDto;
import by.example.bookstore_api.model.dto.response.AuthorResponseDto;
import by.example.bookstore_api.model.entity.Author;
import by.example.bookstore_api.repository.AuthorRepository;
import by.example.bookstore_api.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;
  private final AuthorMapper authorMapper;

  @Cacheable("authorCache")
  public AuthorResponseDto findById(UUID authorId) {
    return authorRepository
        .findById(authorId)
        .map(authorMapper::toAuthorDto)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format("Author with id=%s not found", authorId)));
  }

  public List<AuthorResponseDto> findAll(String filter) {
    List<Author> authors;
    if (filter == null || filter.isEmpty()) {
        authors = authorRepository.findAll();
    } else {
      authors = authorRepository.findByLastnameContaining(filter);
    }
    return authorMapper.toAuthors(authors);
  }

  public void save(AuthorRequestDto authorRequestDto) {
    if (authorRepository.existsByLastnameAndName(
        authorRequestDto.name(), authorRequestDto.lastname())) {
      throw new AuthorExists(
          String.format(
              "Author '%s %s' already exists",
              authorRequestDto.name(), authorRequestDto.lastname()));
    }
    authorRepository.save(authorMapper.toAuthor(authorRequestDto));
  }

  public void deleteById(UUID authorId) {
    authorRepository.deleteById(authorId);
  }

  @Transactional
  @CacheEvict(value = "authorCache")
  public void update(UUID authorId, AuthorRequestDto authorRequestDto) {
    Author author =
        authorRepository
            .findById(authorId)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format("Author with id=%s not found", authorId)));

    author.setName(authorRequestDto.name());
    author.setLastname(authorRequestDto.lastname());
    authorRepository.save(author);
  }

}

package by.example.bookstore_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import by.example.bookstore_api.mapper.AuthorMapper;
import by.example.bookstore_api.model.dto.request.AuthorRequestDto;
import by.example.bookstore_api.model.dto.response.AuthorResponseDto;
import by.example.bookstore_api.model.entity.Author;
import by.example.bookstore_api.repository.AuthorRepository;
import by.example.bookstore_api.service.impl.AuthorServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private UUID authorId;
    private Author author;
    private AuthorRequestDto authorRequestDto;
    private AuthorResponseDto authorResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        authorId = UUID.randomUUID();
        author = new Author();
        author.setId(authorId);
        author.setName("John");
        author.setLastname("Doe");

        authorRequestDto = new AuthorRequestDto("John", "Doe");
        authorResponseDto = new AuthorResponseDto(authorId, "John", "Doe");
    }


    @Test
    void findById_ShouldReturnAuthorResponseDto_WhenAuthorExists() {
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(authorMapper.toAuthorDto(author)).thenReturn(authorResponseDto);

        AuthorResponseDto result = authorService.findById(authorId);

        assertNotNull(result);
        assertEquals(authorId, result.id());
        assertEquals("John", result.name());
        assertEquals("Doe", result.lastname());
        verify(authorRepository).findById(authorId);
        verify(authorMapper).toAuthorDto(author);
    }

    @Test
    void findById_ShouldThrowEntityNotFoundException_WhenAuthorDoesNotExist() {
        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> authorService.findById(authorId));

        assertEquals(String.format("Product with id=%s not found", authorId), exception.getMessage());
        verify(authorRepository).findById(authorId);
        verifyNoInteractions(authorMapper);
    }

    @Test
    void save_ShouldSaveAuthor_WhenAuthorDoesNotExist() {
        when(authorRepository.existsByLastnameAndName(authorRequestDto.name(), authorRequestDto.lastname())).thenReturn(false);
        when(authorMapper.toAuthor(authorRequestDto)).thenReturn(author);

        authorService.save(authorRequestDto);

        verify(authorRepository).existsByLastnameAndName(authorRequestDto.name(), authorRequestDto.lastname());
        verify(authorRepository).save(author);
        verify(authorMapper).toAuthor(authorRequestDto);
    }

    @Test
    void save_ShouldThrowIllegalArgumentException_WhenAuthorAlreadyExists() {
        when(authorRepository.existsByLastnameAndName(authorRequestDto.name(), authorRequestDto.lastname())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> authorService.save(authorRequestDto));

        assertEquals(String.format("Author '%s %s' already exists", authorRequestDto.name(), authorRequestDto.lastname()), exception.getMessage());
        verify(authorRepository).existsByLastnameAndName(authorRequestDto.name(), authorRequestDto.lastname());
        verifyNoInteractions(authorMapper);
    }

    @Test
    void deleteById_ShouldCallRepositoryDeleteById() {
        doNothing().when(authorRepository).deleteById(authorId);

        authorService.deleteById(authorId);

        verify(authorRepository).deleteById(authorId);
    }

    @Test
    void update_ShouldUpdateAuthor_WhenAuthorExists() {
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        authorService.update(authorId, authorRequestDto);

        verify(authorRepository).findById(authorId);
        assertEquals("John", author.getName());
        assertEquals("Doe", author.getLastname());
        verify(authorRepository).save(author);
    }
    @Test
    void findByLastName() {
        when(authorRepository.findByLastname("Doe")).thenReturn(Optional.of(author));
        when(authorMapper.toAuthorDto(author)).thenReturn(authorResponseDto);

        AuthorResponseDto result = authorService.findByLastName("Doe");

        assertNotNull(result);
        assertEquals("John", result.name());
        assertEquals("Doe", result.lastname());
        verify(authorRepository).findByLastname("Doe");
        verify(authorMapper).toAuthorDto(author);
    }
}
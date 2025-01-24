package by.example.bookstore_api.controller;

import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.dto.response.BookResponseDto;
import by.example.bookstore_api.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    void findById_ReturnBook_WhenExists() throws Exception {
        UUID bookId = UUID.randomUUID();
        BookResponseDto responseDto = BookResponseDto.builder()
                .id(bookId)
                .title("Test Book")
                .authorName("Test Author")
                .authorLastName("Test Author Last Name")
                .price(19.99)
                .publishedYear(2023)
                .bookstoreName("Test Book Store")
                .build();

        Mockito.when(bookService.findById(bookId)).thenReturn(responseDto);

        mockMvc.perform(get("/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(bookId.toString()))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.authorName").value("Test Author"))
                .andExpect(jsonPath("$.authorLastName").value("Test Author Last Name"))
                .andExpect(jsonPath("$.price").value(19.99))
                .andExpect(jsonPath("$.publishedYear").value(2023))
                .andExpect(jsonPath("$.bookstoreName").value("Test Book Store"));
    }

    @Test
    void findAll_ShouldReturnListOfBooks() throws Exception {
        UUID bookId1 = UUID.randomUUID();
        UUID bookId2 = UUID.randomUUID();

        List<BookResponseDto> books = Arrays.asList(
                BookResponseDto.builder()
                        .id(bookId1)
                        .title("Book One")
                        .authorName("Author One")
                        .authorLastName("Last Name One")
                        .price(10.50)
                        .publishedYear(2021)
                        .bookstoreName("Store One")
                        .build(),
                BookResponseDto.builder()
                        .id(bookId2)
                        .title("Book Two")
                        .authorName("Author Two")
                        .authorLastName("Last Name Two")
                        .price(15.75)
                        .publishedYear(2022)
                        .bookstoreName("Store Two")
                        .build()
        );


        Mockito.when(bookService.findAll()).thenReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Book One"))
                .andExpect(jsonPath("$[0].authorName").value("Author One"))
                .andExpect(jsonPath("$[0].authorLastName").value("Last Name One"))
                .andExpect(jsonPath("$[0].bookstoreName").value("Store One"))
                .andExpect(jsonPath("$[1].title").value("Book Two"))
                .andExpect(jsonPath("$[1].authorName").value("Author Two"))
                .andExpect(jsonPath("$[1].authorLastName").value("Last Name Two"))
                .andExpect(jsonPath("$[1].bookstoreName").value("Store Two"));
    }

    @Test
    void save_ShouldCreateBook_WhenValidRequest() throws Exception {
        BookRequestDto requestDto = BookRequestDto.builder()
                .title("Test Book")
                .authorName("Test Author")
                .authorLastName("Test Author Last Name")
                .price(19.99)
                .publishedYear(2023)
                .bookstoreName("Test Book Store")
                .build();

        String requestBody = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());

        Mockito.verify(bookService).save(requestDto);
    }

    @Test
    void update_ShouldUpdateBook_WhenValidRequest() throws Exception {

        UUID bookId = UUID.randomUUID();
        BookRequestDto requestDto = BookRequestDto.builder()
                .title("Updated Book")
                .authorName("Updated Author")
                .authorLastName("Updated Author Last Name")
                .price(25.99)
                .publishedYear(2024)
                .bookstoreName("Updated Book Store")
                .build();

        String requestBody = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(put("/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        Mockito.verify(bookService).update(eq(bookId), eq(requestDto));
    }


    @Test
    void deleteById_ShouldDeleteBook_WhenBookExists() throws Exception {
        UUID bookId = UUID.randomUUID();

        mockMvc.perform(delete("/books/{id}", bookId))
                .andExpect(status().isNoContent());

        Mockito.verify(bookService).delete(bookId);
    }
}
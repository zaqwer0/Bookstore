//package by.example.bookstore.api.controller;
//
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import by.example.bookstore.api.model.dto.request.AuthorRequestDto;
//import by.example.bookstore.api.model.dto.response.AuthorResponseDto;
//import by.example.bookstore.api.service.impl.AuthorServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.util.List;
//import java.util.UUID;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//@WebMvcTest(AuthorController.class)
//class AuthorControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AuthorServiceImpl authorService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void findById_ShouldReturnAuthor_WhenAuthorExists() throws Exception {
//        UUID authorId = UUID.randomUUID();
//        AuthorResponseDto responseDto = AuthorResponseDto.builder()
//                .id(authorId)
//                .name("Johny")
//                .lastname("Rogers")
//                .build();
//
//        Mockito.when(authorService.findById(authorId)).thenReturn(responseDto);
//
//        mockMvc.perform(get("/authors/{id}", authorId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(authorId.toString()))
//                .andExpect(jsonPath("$.name").value("Johny"))
//                .andExpect(jsonPath("$.lastname").value("Rogers"));
//    }
//
//    @Test
//    void findAll_ShouldReturnListOfAuthors() throws Exception {
//        UUID authorId = UUID.randomUUID();
//        List<AuthorResponseDto> authors = List.of(new AuthorResponseDto(authorId, "Johny", "Rogers"));
//
//        Mockito.when(authorService.findAll()).thenReturn(authors);
//
//        mockMvc.perform(get("/authors"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value(authorId.toString()))
//                .andExpect(jsonPath("$[0].name").value("Johny"))
//                .andExpect(jsonPath("$[0].lastname").value("Rogers"));
//    }
//
//    @Test
//    void save_ShouldReturnCreatedStatus() throws Exception {
//        AuthorRequestDto requestDto = new AuthorRequestDto("Johny", "Rogers");
//
//        mockMvc.perform(post("/authors")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDto)))
//                .andExpect(status().isCreated());
//
//        Mockito.verify(authorService).save(any(AuthorRequestDto.class));
//    }
//
//    @Test
//    void update_ShouldReturnOkStatus() throws Exception {
//        UUID authorId = UUID.randomUUID();
//        AuthorRequestDto requestDto = new AuthorRequestDto("Johny", "Rogers");
//
//        mockMvc.perform(put("/authors/{id}", authorId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//        Mockito.verify(authorService).update(eq(authorId), any(AuthorRequestDto.class));
//    }
//
//    @Test
//    void deleteById_ShouldReturnNoContentStatus() throws Exception {
//        UUID authorId = UUID.randomUUID();
//
//        mockMvc.perform(delete("/authors/{id}", authorId))
//                .andExpect(status().isNoContent());
//
//        Mockito.verify(authorService).deleteById(authorId);
//    }
//}

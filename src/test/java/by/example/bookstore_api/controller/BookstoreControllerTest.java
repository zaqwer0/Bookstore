package by.example.bookstore_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static java.nio.file.Paths.get;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@WebMvcTest(BookstoreController.class)
class BookstoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private BookstoreServiceImpl bookstoreService;

    @Autowired
    private ObjectMapper objectMapper;



//    @Test
//    void findById_ReturnsBookservice_whenExists() throws Exception {
//        UUID bookstoreId = UUID.randomUUID();
//        BookstoreResponseDto bookstoreResponseDto = BookstoreResponseDto.builder()
//                .id(bookstoreId)
//                .name("Test Bookstore")
//                .build();
//
//        Mockito.when(bookstoreService.findById(bookstoreId)).thenReturn(bookstoreResponseDto);
//
//        mockMvc.perform(get("/bookstores/{id}", bookstoreId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(bookstoreId.toString()))
//                .andExpect(jsonPath("$.name").value("Test Bookstore"));
//    }
}
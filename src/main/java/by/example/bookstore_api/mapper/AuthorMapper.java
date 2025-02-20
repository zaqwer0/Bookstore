package by.example.bookstore_api.mapper;

import by.example.bookstore_api.model.dto.request.AuthorRequestDto;
import by.example.bookstore_api.model.dto.response.AuthorResponseDto;
import by.example.bookstore_api.model.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {

  Author toAuthor(AuthorResponseDto authorResponseDto);

  Author toAuthor(AuthorRequestDto authorRequestDto);

  List<AuthorResponseDto> toAuthors(List<Author> authorDtos);

  AuthorResponseDto toAuthorDto(Author author);
}

package by.example.bookstore_api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //todo why UUID??
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books;
}

package ru.itis.aivar.comics.app.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = {"titles"})
@ToString(exclude = "titles")
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "author")
    private Set<Title> titles;
}

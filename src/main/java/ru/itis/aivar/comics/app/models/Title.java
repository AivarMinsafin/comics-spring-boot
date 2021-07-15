package ru.itis.aivar.comics.app.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = {"users", "chapters", "author"})
@ToString(exclude = {"chapters", "users"})
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imagePath;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToMany(mappedBy = "titles")
    private Set<User> users;
    @OneToMany(mappedBy = "title", orphanRemoval = true)
    private Set<Chapter> chapters;
    @ManyToOne
    private Author author;

}

package ru.itis.aivar.comics.app.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "pages")
@EqualsAndHashCode(exclude = "pages")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer chapterNumber;
    @ManyToOne
    private Title title;
    @OneToMany(mappedBy = "chapter", orphanRemoval = true)
    private Set<Page> pages;

}

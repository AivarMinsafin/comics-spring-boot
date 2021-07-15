package ru.itis.aivar.comics.app.models;

import lombok.*;
import org.springframework.security.core.Transient;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Target;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
@ToString(exclude = {"author", "titles"})
@EqualsAndHashCode(exclude = {"author", "titles"})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String username;
    private String hashPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Title> titles;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Author author;

    @Enumerated(value = EnumType.STRING)
    private State state;
    private String confirmCode;

    public enum State {
        ACTIVE, NOT_CONFIRMED, BANNED
    }

    public boolean isActive(){
        return this.state == State.ACTIVE;
    }

    public boolean isBanned(){
        return this.state == State.BANNED;
    }
}

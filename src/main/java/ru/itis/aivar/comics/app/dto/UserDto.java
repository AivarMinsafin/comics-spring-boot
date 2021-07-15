package ru.itis.aivar.comics.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.aivar.comics.app.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String email;
    private String username;
    private boolean isAuthor;

    public static UserDto from(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .id(user.getId())
                .username(user.getUsername())
                .isAuthor(false)
                .build();
    }

    public static List<UserDto> from(List<User> users){
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}

package ru.itis.aivar.comics.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Settings {

    private String username;
    private String password;
    private String email;
    private String baseUrl;
    private String titleBlankImage;

}

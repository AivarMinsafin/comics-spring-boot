package ru.itis.aivar.comics.app.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TitleCreationForm {

    private String name;
    private String description;
    private MultipartFile image;
    private String email;

}

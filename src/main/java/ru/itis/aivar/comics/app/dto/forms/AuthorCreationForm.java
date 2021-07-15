package ru.itis.aivar.comics.app.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.aivar.comics.app.validation.PhoneNumber;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorCreationForm {

    private Long userId;
    @Size(min = 2, message = "{errors.invalid.general.field.size}")
    private String firstname;
    @Size(min = 2, message = "{errors.invalid.general.field.size}")
    private String lastname;
    @Size(min = 11, max = 12)
    @PhoneNumber
    private String phoneNumber;

}

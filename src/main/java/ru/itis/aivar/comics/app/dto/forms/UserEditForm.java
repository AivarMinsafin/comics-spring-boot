package ru.itis.aivar.comics.app.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.aivar.comics.app.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEditForm {

    private Long id;
    @NotEmpty
    @Email(message = "{errors.incorrect.email}")
    private String newEmail;
    @NotEmpty
    @Size(min = 5, message = "{errors.invalid.username}")
    private String newUsername;
    @NotEmpty
    @ValidPassword(message = "{errors.invalid.password}")
    private String passwordForConfirmAction;

}

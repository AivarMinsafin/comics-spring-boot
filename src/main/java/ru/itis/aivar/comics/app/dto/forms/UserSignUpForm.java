package ru.itis.aivar.comics.app.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.aivar.comics.app.validation.TwoFieldsMatch;
import ru.itis.aivar.comics.app.validation.ValidPassword;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TwoFieldsMatch(
        message = "{errors.invalid.password.verification.match}",
        first = "password",
        second = "passwordVerification"
)
public class UserSignUpForm {
    @Email(message = "{errors.incorrect.email}")
    private String email;
    @Size(min = 5, message = "{errors.invalid.username}")
    private String username;
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;
    private String passwordVerification;

}

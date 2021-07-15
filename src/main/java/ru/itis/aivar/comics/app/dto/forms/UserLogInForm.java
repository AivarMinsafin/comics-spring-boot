package ru.itis.aivar.comics.app.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.aivar.comics.app.validation.ValidPassword;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogInForm {

    @Email
    private String email;
    @ValidPassword
    private String password;

}

package ru.itis.aivar.comics.app.services;

import ru.itis.aivar.comics.app.dto.forms.UserSignUpForm;

public interface SignUpService {

    void signUp(UserSignUpForm form);

    void confirm(String confirmCode);
}

package ru.itis.aivar.comics.app.utils.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class EmailUtilFakeImpl implements EmailUtil {
    @Override
    public void sendMail(String to, String subject, String from, String text) {
        System.out.println(text);
    }
}

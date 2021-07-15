package ru.itis.aivar.comics.app.utils.email;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String code);
}

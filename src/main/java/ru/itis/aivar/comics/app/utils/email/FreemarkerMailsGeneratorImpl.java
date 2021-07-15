package ru.itis.aivar.comics.app.utils.email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class FreemarkerMailsGeneratorImpl implements MailsGenerator {

    private Configuration configuration;

    public FreemarkerMailsGeneratorImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String getMailForConfirm(String serverUrl, String code) {
        Template template = null;
        try {
            template = configuration.getTemplate("mails/confirm_mail.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        template.setOutputEncoding("UTF-8");
        Map<String, String> attributes = new HashMap<>();
        attributes.put("confirm_code", code);
        attributes.put("server_url", serverUrl);

        StringWriter writer = new StringWriter();
        try {
            template.process(attributes, writer);
        } catch (TemplateException | IOException e){
            throw new IllegalStateException(e);
        }
        return writer.toString();
    }
}

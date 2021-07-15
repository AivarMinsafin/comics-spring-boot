package ru.itis.aivar.comics.app.extensions.freemarker;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPropertyMethod implements TemplateMethodModelEx {

    @Autowired
    Environment environment;

    @Override
    public Object exec(List list) throws TemplateModelException {
        if (list.size() != 1){
            throw new TemplateModelException("Just one arg is required");
        }
        return environment.getProperty(String.valueOf(list.get(0)));
    }
}

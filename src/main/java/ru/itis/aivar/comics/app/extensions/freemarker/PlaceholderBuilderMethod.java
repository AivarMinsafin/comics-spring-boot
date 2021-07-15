package ru.itis.aivar.comics.app.extensions.freemarker;

import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PlaceholderBuilderMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        if (list.size() == 2){
            String controllerMethod = String.valueOf(list.get(0));
            MvcUriComponentsBuilder.MethodArgumentBuilder builder = MvcUriComponentsBuilder.fromMappingName(controllerMethod);
            SimpleSequence sequence = (SimpleSequence) list.get(1);
            List<String> params = sequence.toList();
            String placeholderString = String.join("/", params);
            return Paths.get(builder.build(), placeholderString).toString();
        }else {
            throw new TemplateModelException();
        }
    }
}

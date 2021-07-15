package ru.itis.aivar.comics.app.extensions.freemarker;

import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MvcUriTemplateMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        if (list.size() < 1) {
            throw new TemplateModelException("At least one argument should be exist");
        }
        String controllerMethod = String.valueOf(list.get(0));
        if (list.size() == 1) {
            return MvcUriComponentsBuilder.fromMappingName(controllerMethod).build();
        }
        if (list.size() == 2) {
            SimpleSequence sequence = (SimpleSequence) list.get(1);
            List<String> params = sequence.toList();

            MvcUriComponentsBuilder.MethodArgumentBuilder builder = MvcUriComponentsBuilder.fromMappingName(controllerMethod);
            for (int i = 0; i < params.size(); i++) {
                builder = builder.arg(i, params.get(i));
            }
            return builder.build();
        }
//        if (list.size() == 3){
//            List<String> params1 = ((SimpleSequence) list.get(1)).toList();
//            List<String> params2 = ((SimpleSequence) list.get(2)).toList();
//            String first1 = params1.get(0);
//            String first2 = params2.get(0);
//            MvcUriComponentsBuilder.MethodArgumentBuilder builder = MvcUriComponentsBuilder.fromMappingName(controllerMethod);
//            int argI = 0;
//            List<String> getParams = new ArrayList<>();
//            if (first1.equals("p")){
//                for (int i = 1; i < params1.size(); i++) {
//                    builder = builder.arg(argI++, params1.get(i));
//                }
//            }
//            if (first1.equals("g")){
//                for (int i = 1; i < params1.size(); i++) {
//                    getParams.add(params1.get(i));
//                }
//            }
//            if (first2.equals("p")){
//                for (int i = 1; i < params2.size(); i++) {
//                    builder = builder.arg(argI++, params2.get(i));
//                }
//            }
//            if (first2.equals("g")){
//                for (int i = 1; i < params2.size(); i++) {
//                    getParams.add(params2.get(i));
//                }
//            }
//            String url = builder.build();
//            if (!getParams.isEmpty()){
//                url = url.concat("?");
//                StringBuilder str = new StringBuilder(url);
//                getParams.forEach(str::append);
//                url = str.toString();
//            }
//            return url;
//        }
        return MvcUriComponentsBuilder.fromMappingName(controllerMethod).build();
    }
}

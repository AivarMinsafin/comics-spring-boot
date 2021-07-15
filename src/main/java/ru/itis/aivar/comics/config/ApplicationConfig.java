package ru.itis.aivar.comics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.itis.aivar.comics.app.exceptions.AppExceptionResolver;
import ru.itis.aivar.comics.app.extensions.freemarker.GetPropertyMethod;
import ru.itis.aivar.comics.app.extensions.freemarker.MvcUriTemplateMethod;
import ru.itis.aivar.comics.app.extensions.freemarker.PlaceholderBuilderMethod;
import ru.itis.aivar.comics.app.interceptors.ChapterAuthorCheckInterceptor;
import ru.itis.aivar.comics.app.interceptors.TitleAuthorCheckInterceptor;
import ru.itis.aivar.comics.app.security.config.SecurityConfig;
import ru.itis.aivar.comics.app.utils.converters.ChapterDtoToStringConverter;
import ru.itis.aivar.comics.app.utils.converters.StringToChapterConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("ru.itis.aivar.comics.app")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.itis.aivar.comics.app.repositories")
@Import({LocalizationConfig.class, SecurityConfig.class})
public class ApplicationConfig implements WebMvcConfigurer {

    @Autowired
    private StringToChapterConverter stringToChapterConverter;

    @Autowired
    private TitleAuthorCheckInterceptor titleAuthorCheckInterceptor;
    @Autowired
    private ChapterAuthorCheckInterceptor chapterAuthorCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(titleAuthorCheckInterceptor);
        registry.addInterceptor(chapterAuthorCheckInterceptor);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToChapterConverter);
        registry.addConverter(new ChapterDtoToStringConverter());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ExecutorService executorService(){
        return Executors.newCachedThreadPool();
    }

    @Autowired
    private GetPropertyMethod getPropertyMethod;

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");

        Map<String, Object> freemarkerVariables = new HashMap<>();
        freemarkerVariables.put("mvcUri", new MvcUriTemplateMethod());
        freemarkerVariables.put("prop", getPropertyMethod);
        freemarkerVariables.put("pathPlaceholder", new PlaceholderBuilderMethod());

        freeMarkerConfigurer.setFreemarkerVariables(freemarkerVariables);
        return freeMarkerConfigurer;
    }

    @Bean
    public ViewResolver viewResolver(){
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setCache(false);
        freeMarkerViewResolver.setSuffix(".ftlh");
        freeMarkerViewResolver.setPrefix("");
        freeMarkerViewResolver.setContentType("text/html; charset=utf-8");
        return freeMarkerViewResolver;
    }

}

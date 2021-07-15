package ru.itis.aivar.comics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Collections;

@SpringBootApplication
public class ComicsSpringBootApplication {

	public ComicsSpringBootApplication(FreeMarkerConfigurer configurer){
		configurer.getTaglibFactory().setClasspathTlds(Collections.singletonList("/META-INF/security.tld"));
	}

	public static void main(String[] args) {
		SpringApplication.run(ComicsSpringBootApplication.class, args);
	}

}

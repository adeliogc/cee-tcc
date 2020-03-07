package br.com.tcc.cee;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class CeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeeApplication.class, args);
	}
	
	@Bean
	public LocaleResolver locale() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@Bean
	public IDialect conditionalCommentDialect() {
		return new Java8TimeDialect();
	}
}

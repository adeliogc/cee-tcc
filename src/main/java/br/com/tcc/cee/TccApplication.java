package br.com.tcc.cee;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
@EntityScan(basePackages = "br.com.tcc.cee.modelo")
public class TccApplication {

	public static void main(String[] args) {
		SpringApplication.run(TccApplication.class, args);
	}

	@Bean
	public LocaleResolver locale() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}

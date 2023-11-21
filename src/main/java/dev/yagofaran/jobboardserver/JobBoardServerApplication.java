package dev.yagofaran.jobboardserver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Job Board",
		description = "Job board manager API",
		version = "1"
	)
)
@SecurityScheme(
	name = "jwt_auth",
	scheme = "bearer",
	bearerFormat = "JWT",
	type = SecuritySchemeType.HTTP,
	in = SecuritySchemeIn.HEADER
)
public class JobBoardServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobBoardServerApplication.class, args);
		Locale.setDefault(Locale.of("en", "US"));
	}

}

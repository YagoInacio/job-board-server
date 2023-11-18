package dev.yagofaran.jobboardserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class JobBoardServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobBoardServerApplication.class, args);
		Locale.setDefault(Locale.of("en", "US"));
	}

}

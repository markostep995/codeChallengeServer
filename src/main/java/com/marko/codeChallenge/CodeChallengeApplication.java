package com.marko.codeChallenge;

		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
		import org.springframework.context.annotation.Bean;
		import org.springframework.scheduling.annotation.EnableAsync;
		import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
		import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
public class CodeChallengeApplication extends SpringBootServletInitializer {
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(CodeChallengeApplication.class, args);
	}

}


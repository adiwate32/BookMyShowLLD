package com.lld.bookmyshow;

import com.lld.bookmyshow.controllers.UserController;
import com.lld.bookmyshow.dtos.SignUpRequestDto;
import com.lld.bookmyshow.dtos.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookmyshowApplication implements CommandLineRunner {
	@Autowired
	private UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookmyshowApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		SignUpRequestDto requestDto = new SignUpRequestDto();
		requestDto.setEmail("abhishek@scaler.com");
		requestDto.setPassword("password");

		SignUpResponseDto responseDto = userController.signUp(requestDto);
		System.out.println("DEBUG");
	}
}

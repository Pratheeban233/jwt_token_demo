package com.example;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JwtDemoApplication {

	@Autowired
	private UserRepository  userRepository;

	@PostConstruct
	void init(){
		List<User> users = Stream.of(new User(1, "prathi233", "test123", "prathi@gmail.com"),
				new User(1, "prathi", "test", "p@gmail.com"),
				new User(1, "abiya", "papa", "abi@gmail.com"))
				.collect(Collectors.toList());

		 userRepository.saveAll(users);
	}




	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}

}

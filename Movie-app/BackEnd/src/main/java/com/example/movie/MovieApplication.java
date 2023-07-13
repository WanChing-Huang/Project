package com.example.movie;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class MovieApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}
    
	
	
}

package com.tcna.primeraweb;

import com.tcna.primeraweb.entities.Animal;
import com.tcna.primeraweb.repository.AnimalRepository;
import com.tcna.primeraweb.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PrimeraWebSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeraWebSpringBootApplication.class, args);
	}


}

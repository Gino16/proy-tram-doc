package com.example.demo;

import com.example.demo.models.service.SIUploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProyTramDocBetaApplication implements CommandLineRunner {

	@Autowired
    SIUploadFile uploadFileService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ProyTramDocBetaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();

		String password = "12345";

		for (int i=0; i<3; i++){
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
	}
}

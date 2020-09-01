package com.example.demo;

import com.example.demo.models.service.SIUploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyTramDocBetaApplication implements CommandLineRunner {

	@Autowired
    SIUploadFile uploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(ProyTramDocBetaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
	}
}

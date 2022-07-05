package com.tim123.vaccinationmain;

import com.tim123.vaccinationmain.service.IzvestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaccinationMainApplication implements CommandLineRunner {

	@Autowired
	private IzvestajService izvestajService;

	public static void main(String[] args) {
		SpringApplication.run(VaccinationMainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//za testiranje
		izvestajService.generisiHTML("1");
//		System.out.println("Generisem pdf");
//		saglasnostService.generisiPDF("1");
	}
}

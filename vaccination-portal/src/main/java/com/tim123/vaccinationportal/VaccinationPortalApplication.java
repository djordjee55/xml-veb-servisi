package com.tim123.vaccinationportal;

import com.tim123.vaccinationportal.service.InteresovanjeService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import com.tim123.vaccinationportal.service.SertifikatService;
import com.tim123.vaccinationportal.util.HTMLTransformer;
import com.tim123.vaccinationportal.util.PDFTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaccinationPortalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VaccinationPortalApplication.class, args);
	}

	@Autowired
	private SertifikatService sertifikatService;

	@Autowired
	private InteresovanjeService interesovanjeService;

	@Autowired
	private SaglasnostService saglasnostService;

	@Override
	public void run(String... args) throws Exception {
		//za testiranje
//		System.out.println("Generisem html");
//		saglasnostService.generisiHTML("1");
		System.out.println("Generisem pdf");
		saglasnostService.generisiPDF("1");
	}
}

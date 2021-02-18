package dev.raycool.polaction;

import dev.raycool.polaction.model.PoliticalOfficialsResponse;
import dev.raycool.polaction.model.PoliticalOfficial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class PolActionApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);

	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(PolActionApplication.class, args);
	}

	@Bean
	public RestTemplate createRestTemplate(){
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		String googleApiKey = "";
		String address = "39.674768, -75.6592079";
		String formattedGoogleApiUrl = String.format("https://www.googleapis.com/civicinfo/v2/representatives/?&address=%s&includeOffices=true&key=%s", address, googleApiKey);

		ResponseEntity<PoliticalOfficialsResponse> response = restTemplate.getForEntity(formattedGoogleApiUrl, PoliticalOfficialsResponse.class);

		PoliticalOfficial[] allOfficials = response.getBody().getOfficials();
		logger.info("\n" + "++++++++++++++++++++++++++++++++" + "\n");
		for(PoliticalOfficial politicalOfficial : allOfficials) {
			logger.info(politicalOfficial.toString());
			logger.info(politicalOfficial.getName());
			logger.info(politicalOfficial.getAddress()[0].getLine1());
			logger.info(politicalOfficial.getAddress()[0].getCity());
			logger.info(politicalOfficial.getAddress()[0].getState());
			logger.info(politicalOfficial.getParty());
			logger.info(politicalOfficial.getPhotoUrl());
			logger.info(politicalOfficial.getPhones()[0].getPhone());
			if (politicalOfficial.getUrls() != null) {
				logger.info(politicalOfficial.getUrls()[0].getUrl());
			}
			logger.info("\n");
		}
	}
}

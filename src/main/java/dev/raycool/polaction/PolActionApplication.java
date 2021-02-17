package dev.raycool.polaction;

import dev.raycool.polaction.model.OfficialsResponse;
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
		String address = "19977";
		String formattedGoogleApiUrl = String.format("https://www.googleapis.com/civicinfo/v2/representatives/?&address=%s&includeOffices=true&key=%s", address, googleApiKey);

		ResponseEntity<OfficialsResponse> response = restTemplate.getForEntity(formattedGoogleApiUrl, OfficialsResponse.class);

		PoliticalOfficial[] allOfficials = response.getBody().getOfficials();
		for(PoliticalOfficial politicalOfficial : allOfficials) {
			logger.info(politicalOfficial.toString());
			logger.info(politicalOfficial.getName());
			logger.info(politicalOfficial.getAddress()[0].getLine1());
			logger.info(politicalOfficial.getAddress()[0].getCity());
			logger.info(politicalOfficial.getParty() + "\n");
		}
	}
}

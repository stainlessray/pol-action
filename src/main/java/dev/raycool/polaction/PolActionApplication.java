package dev.raycool.polaction;

import dev.raycool.polaction.officesmodels.PoliticalOffice;
import dev.raycool.polaction.officesmodels.PoliticalOfficesResponse;
import dev.raycool.polaction.officialsmodels.PoliticalOfficialsResponse;
import dev.raycool.polaction.officialsmodels.PoliticalOfficial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//todo
// Implement error handling for template population actions.
// directly handle with try, catch, finally.
// user flow may need to offer less refined results to users who experience problems.
//

@SpringBootApplication
public class PolActionApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	RestTemplate restTemplate2;

	public static void main(String[] args) {
		SpringApplication.run(PolActionApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public RestTemplate restTemplate2(){
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		String googleApiKey = "";
		//String address = "39.278171, -75.602260"; //39.278171, 75.602260
		String address = "538 Barley Ct, Smyrna, Delaware, 19977";

		String formattedGoogleApiUrl = String.format("https://www.googleapis.com/civicinfo/v2/representatives/?&address=%s&includeOffices=true&key=%s", address, googleApiKey);

		ResponseEntity<PoliticalOfficialsResponse> response = restTemplate.getForEntity(formattedGoogleApiUrl, PoliticalOfficialsResponse.class);

		PoliticalOfficial[] allOfficials = response.getBody().getOfficials();
		logger.info("\n" + "++++++++++++++++++++++++++++++++" + "\n");
		for(PoliticalOfficial politicalOfficial : allOfficials) {
			logger.info(politicalOfficial.toString());
			logger.info(politicalOfficial.getName());
			logger.info(politicalOfficial.getAddress()[0].toString());
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
		ResponseEntity<PoliticalOfficesResponse> response2 = restTemplate2.getForEntity(formattedGoogleApiUrl, PoliticalOfficesResponse.class);
		PoliticalOffice[] allOffices = response2.getBody().getOffices();
		logger.info("\n" + "++++++++++++++++++++++++++++++++" + "\n");
		logger.info(allOffices[0].getLevels()[0].getLevel());
		for(PoliticalOffice politicalOffice : allOffices) {
			logger.info(politicalOffice.getName());
			logger.info(politicalOffice.getDivisionId());

			logger.info("length of indices array = " + politicalOffice.getOfficialIndices().length);
			logger.info(String.valueOf(politicalOffice.getOfficialIndices()[0].getOfficialIndices()));
			if (politicalOffice.getOfficialIndices().length == 2) {
				logger.info(String.valueOf(politicalOffice.getOfficialIndices()[1].getOfficialIndices()));
			}
			logger.info("\n");

		}
	}
}

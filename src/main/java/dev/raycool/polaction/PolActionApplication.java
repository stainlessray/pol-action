package dev.raycool.polaction;

import dev.raycool.polaction.officesmodels.PoliticalOffice;
import dev.raycool.polaction.officesmodels.PoliticalOfficesResponse;
import dev.raycool.polaction.officialsmodels.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

//todo
// Implement error handling for template population actions.
// directly handle with try, catch, finally.
// user flow may need to offer less refined results to users who experience problems.
//

@SpringBootApplication
public class PolActionApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);
	@Value("${apikey}")
	private String googleApiKey;

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	RestTemplate restTemplate2;

	@Bean
	public RestTemplate restTemplate(){ return new RestTemplate(); }

	@Bean
	public RestTemplate restTemplate2(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(PolActionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String stateOfPennsylvania = "Pennsylvania";
		String stateOfDelaware = "Delaware";
		String address = "39.278171, -75.602260";
		String formattedGoogleApiUrl = String.format("https://www.googleapis.com/civicinfo/v2/representatives/?&address=%s&includeOffices=true&key=%s", stateOfPennsylvania, googleApiKey);

		ResponseEntity<PoliticalOfficialsResponse> response = restTemplate.getForEntity(formattedGoogleApiUrl, PoliticalOfficialsResponse.class);
		PoliticalOfficial[] allOfficials = Objects.requireNonNull(response.getBody()).getOfficials();

		ResponseEntity<PoliticalOfficesResponse> response2 = restTemplate2.getForEntity(formattedGoogleApiUrl, PoliticalOfficesResponse.class);
		PoliticalOffice[] allOffices = Objects.requireNonNull(response2.getBody()).getOffices();

		logger.info("++++++++++++++++++++++++++++++++");
		for(PoliticalOffice politicalOffice : allOffices) {
			logger.info(politicalOffice.getName());
			if (politicalOffice.getRoles() != null) {
				logger.info(politicalOffice.getRoles()[0].getRole());
			}

			for (int i = 0; i < politicalOffice.getOfficialIndices().length; i++ ) {
				//logger.info(politicalOffice.getName());
				PoliticalOfficial politicalOfficial = allOfficials[politicalOffice.getOfficialIndices()[i].getOfficialIndices()];
				logger.info(politicalOfficial.getName());
				if (politicalOfficial.getAddress() != null) {
					logger.info(politicalOfficial.getAddress()[0].getLine1());
					logger.info(politicalOfficial.getAddress()[0].getCity());
					logger.info(politicalOfficial.getAddress()[0].getState());
					logger.info(politicalOfficial.getParty());
					logger.info(politicalOfficial.getPhotoUrl());
					}
					if (politicalOfficial.getPhones() != null) {
						for (Phone phoneNumber : politicalOfficial.getPhones()) {
							logger.info(phoneNumber.getPhone());
					}
					if (politicalOfficial.getUrls() != null) {
						for (Url url : politicalOfficial.getUrls()) {
							logger.info(url.getUrl());
						}
					}
					if (politicalOfficial.getChannels() != null) {
						for (Channel channel : politicalOfficial.getChannels()) {
							logger.info(channel.getType() + " handle: " + channel.getId());
						}
					}
				}
				logger.info("++++++++++++++++++++++++++++++++" + "\n");
			}
		}
	}
}

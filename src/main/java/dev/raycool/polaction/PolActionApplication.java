package dev.raycool.polaction;

import dev.raycool.polaction.officesresponsemodels.PoliticalOffice;
import dev.raycool.polaction.officialsresponsemodels.Channel;
import dev.raycool.polaction.officialsresponsemodels.Phone;
import dev.raycool.polaction.officialsresponsemodels.PoliticalOfficial;
import dev.raycool.polaction.officialsresponsemodels.Url;
import dev.raycool.polaction.service.PoliticalOfficialsResponse;
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

	public static void main(String[] args) {
		SpringApplication.run(PolActionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String stateOfPennsylvania = "Pennsylvania";
		String paZipCode = "15001";
		String deZipCode = "19977";
		String stateOfDelaware = "Delaware";
		String address = "39.278171, -75.602260";
		String formattedGoogleApiUrl = String.format("https://www.googleapis.com/civicinfo/v2/representatives/?&address=%s&includeOffices=true&key=%s", deZipCode, googleApiKey);

		ResponseEntity<PoliticalOfficialsResponse> response = restTemplate.getForEntity(formattedGoogleApiUrl, PoliticalOfficialsResponse.class);
		PoliticalOfficial[] allOfficials = Objects.requireNonNull(response.getBody()).getOfficials();
		PoliticalOffice[] allOffices = Objects.requireNonNull(response.getBody()).getOffices();
		System.out.println(allOfficials[0].toString());

		logger.info("\n++++++++++++++++++++++++++++++++");
		for(PoliticalOffice politicalOffice : allOffices) {
			logger.info(politicalOffice.getName());
			for (int i = 0; i < politicalOffice.getLevels().length; i++) {
				logger.info(politicalOffice.getLevels()[i].toString());
			}
			if (politicalOffice.getRoles() != null) {
				logger.info(politicalOffice.getRoles()[0].getRole());
			}

			for (int i = 0; i < politicalOffice.getOfficialIndices().length; i++ ) {
				PoliticalOfficial politicalOfficial = allOfficials[politicalOffice.getOfficialIndices()[i].getOfficialIndices()];
				logger.info(politicalOfficial.getName());
				logger.info(politicalOfficial.toString());

				if (politicalOfficial.getAddress() != null) {

					logger.info(politicalOfficial.getAddress()[0].toString());
					logger.info(politicalOfficial.getParty());
					logger.info(politicalOfficial.getPhotoUrl());
				}
				if (politicalOfficial.getEmails() != null) {
					logger.info(politicalOfficial.getEmails()[0].toString());
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

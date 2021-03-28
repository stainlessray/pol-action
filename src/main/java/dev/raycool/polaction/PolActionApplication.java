package dev.raycool.polaction;

import dev.raycool.polaction.officesresponsemodels.PoliticalOffice;
import dev.raycool.polaction.officialsresponsemodels.*;
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


@SpringBootApplication
public class PolActionApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);
	@Value("${apikey}")
	private String googleApiKey;
	StringBuilder politicalContactProfile = new StringBuilder("\n");

	@Autowired
	RestTemplate restTemplate;


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
		String locationName = String.valueOf(response.getBody().getNormalizedInput().toString());


		logger.info("\n++++++++++++++++++++++++++++++++");
		appendToContactCard("\n+++++++Top Of Profile+++++++");
		logger.info(locationName);
		appendToContactCard(locationName);
		for(PoliticalOffice politicalOffice : allOffices) {
			logger.info(politicalOffice.getName());
			appendToContactCard("\nOFFICE");
			appendToContactCard(politicalOffice.getName());
			appendToContactCard("API Levels:");
			for (int i = 0; i < politicalOffice.getLevels().length; i++) {
				logger.info(politicalOffice.getLevels()[i].toString());
				appendToContactCard(politicalOffice.getLevels()[i].toString());
			}

			if (politicalOffice.getRoles() != null) {
				appendToContactCard("API Roles:");
				for (int i = 0; i < politicalOffice.getRoles().length; i++) {
					appendToContactCard(politicalOffice.getRoles()[i].toString());
					logger.info(politicalOffice.getRoles()[i].getRole());
				}
			}

			for (int i = 0; i < politicalOffice.getOfficialIndices().length; i++ ) {
				PoliticalOfficial politicalOfficial = allOfficials[politicalOffice.getOfficialIndices()[i].getOfficialIndex()];
				logger.info(politicalOfficial.getName());
				//appendToContactCard("\nOFFICIAL");
				appendToContactCard(politicalOfficial.getName());

				if (politicalOfficial.getAddress() != null) {
					logger.info("Address: " + politicalOfficial.getAddress()[0].toString());
					appendToContactCard("Address: " + politicalOfficial.getAddress()[0].toString());
				}
				if (politicalOfficial.getParty() != null) {
					logger.info("Affiliation: " + politicalOfficial.getParty());
					appendToContactCard("Affiliation: " + politicalOfficial.getParty());
				}
				if (politicalOfficial.getPhotoUrl() != null) {
					logger.info("Photo: " + politicalOfficial.getPhotoUrl());
					appendToContactCard("Photo: " + politicalOfficial.getPhotoUrl());
				}
				if (politicalOfficial.getEmails() != null) {
					for (Email emailAddress : politicalOfficial.getEmails()) {
						logger.info(emailAddress.getEmail());
						appendToContactCard("Email: " + emailAddress.getEmail());
					}
				}

				if (politicalOfficial.getPhones() != null) {
					appendToContactCard("Phones: ");
					for (Phone phoneNumber : politicalOfficial.getPhones()) {
						logger.info(phoneNumber.getPhone());
						appendToContactCard(phoneNumber.getPhone());
					}
					if (politicalOfficial.getUrls() != null) {
						for (Url url : politicalOfficial.getUrls()) {
							logger.info(url.getUrl());
							appendToContactCard("Url: " + url.getUrl());
						}
					}
					if (politicalOfficial.getChannels() != null) {
						for (Channel channel : politicalOfficial.getChannels()) {
							logger.info(channel.getType() + " handle: " + channel.getId());
							appendToContactCard(channel.getType() + " handle: " + channel.getId());
						}
					}
				}
				logger.info("++++++++++++++++++++++++++++++++" + "\n");
			}
		}
		appendToContactCard("\n+++++++Bottom Of Profile+++++++");

		System.out.println(politicalContactProfile);

	}

	public StringBuilder appendToContactCard(String contactElement) {
		politicalContactProfile.append(contactElement).append("\n");
		return politicalContactProfile;
	}
}

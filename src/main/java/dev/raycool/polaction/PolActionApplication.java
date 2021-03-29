package dev.raycool.polaction;

import dev.raycool.polaction.models.PoliticalContactProfile;
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
	StringBuilder politicalContactProfileString = new StringBuilder("\n");
	String locationParser = "";
	String locationToSearch;

	@Value("${apikey}")
	private String googleApiKey;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate(){ return new RestTemplate(); }

	public static void main(String[] args) {
		SpringApplication.run(PolActionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (String argument : args) {
			locationParser += argument += " ";
		}
		System.out.println(locationParser);



		int countOfOfficials = 0;
		int countOfOffices = 0;
		int countOfOfficialsPerOffice = 0;

		String stateOfPennsylvania = "Pennsylvania";
		String paZipCode = "19348";
		String deZipCode = "19977";
		String kentCounty = "Kent County, Delaware";
		String stateOfDelaware = "Delaware";
		String coordinatesDE = "39.278171, -75.602260";
		String coordinatesPA = "39.87192200423589, -75.73844559143794";
		String localizedLocation = "East Marlborough Township, PA 19348";
		String coloradoAirport = "7770 Milton E Proby Pkwy, Colorado Springs, CO 80916";
		String doverDEAddress = "83 Greentree Dr, Dover, DE 19904";
		String wilmingtonDE = "Wilmington, Delaware";
		String newJerseyAddress = "974 E Elmer Rd, Vineland, NJ 08360";

		if (locationParser != "") {
			locationToSearch = locationParser;
		} else locationToSearch = coloradoAirport;

		String formattedGoogleApiUrl = String.format("https://www.googleapis.com/civicinfo/v2/representatives/?&address=%s&includeOffices=true&key=%s", locationToSearch, googleApiKey);

		ResponseEntity<PoliticalOfficialsResponse> response = restTemplate.getForEntity(formattedGoogleApiUrl, PoliticalOfficialsResponse.class);
		PoliticalOfficial[] allOfficials = Objects.requireNonNull(response.getBody()).getOfficials();
		PoliticalOffice[] allOffices = Objects.requireNonNull(response.getBody()).getOffices();
		String locationData = response.getBody().getNormalizedInput().toString();

		logger.info("\n++++++++++++++++++++++++++++++++");
		logger.info(locationData);
		appendToContactString("\n+++++++ " + locationData + " +++++++");

		for(PoliticalOffice politicalOffice : allOffices) {
			countOfOffices += 1;
			countOfOfficialsPerOffice = 0;
			logger.info(politicalOffice.getName());
			appendToContactString("\nOFFICE ===================================");
			appendToContactString(politicalOffice.getName());

			for (int i = 0; i < politicalOffice.getLevels().length; i++) {
				logger.info(politicalOffice.getLevels()[i].toString());
			}

			if (politicalOffice.getRoles() != null) {
				//appendToContactString("API Roles:");
				for (int i = 0; i < politicalOffice.getRoles().length; i++) {
					//appendToContactString(politicalOffice.getRoles()[i].toString());
					logger.info(politicalOffice.getRoles()[i].getRole());
				}
			}

			for (int i = 0; i < politicalOffice.getOfficialIndices().length; i++ ) {
				PoliticalOfficial politicalOfficial = allOfficials[politicalOffice.getOfficialIndices()[i].getOfficialIndex()];
				countOfOfficials += 1;
				countOfOfficialsPerOffice +=1;
				logger.info(politicalOfficial.getName());
				appendToContactString(politicalOfficial.getName());

				if (politicalOfficial.getAddress() != null) {
					logger.info("Address: " + politicalOfficial.getAddress()[0].toString());
					appendToContactString("Address: " + politicalOfficial.getAddress()[0].toString());
				}

				if (politicalOfficial.getParty() != null) {
					logger.info("Affiliation: " + politicalOfficial.getParty());
					appendToContactString("Affiliation: " + politicalOfficial.getParty());
				}

				if (politicalOfficial.getPhotoUrl() != null) {
					logger.info("Photo: " + politicalOfficial.getPhotoUrl());
					appendToContactString("Photo: " + politicalOfficial.getPhotoUrl());
				}

				if (politicalOfficial.getEmails() != null) {
					for (Email emailAddress : politicalOfficial.getEmails()) {
						logger.info(emailAddress.getEmail());
						appendToContactString("Email: " + emailAddress.getEmail());
					}
				}

				if (politicalOfficial.getPhones() != null) {
					appendToContactString("Phones: ");
					for (Phone phoneNumber : politicalOfficial.getPhones()) {
						logger.info(phoneNumber.getPhone());
						appendToContactString(phoneNumber.getPhone());
					}

				if (politicalOfficial.getUrls() != null) {
					for (Url url : politicalOfficial.getUrls()) {
						logger.info(url.getUrl());
						appendToContactString("Website: " + url.getUrl());
					}
				}

				if (politicalOfficial.getChannels() != null) {
					for (Channel channel : politicalOfficial.getChannels()) {
						logger.info(channel.getType() + " handle: " + channel.getId());
						appendToContactString(channel.getType() + " handle: " + channel.getId());
						}
					}

				}
				logger.info("++++++++++++++++++++++++++++++++" + "\n");
				appendToContactString("\n");


			}
			appendToContactString( "Number of officials occupying office of " + politicalOffice.getName() + " = " + countOfOfficialsPerOffice);
			appendToContactString("--------------------------------------");
			appendToContactString("\n");
		}
		appendToContactString("\n+++++++ " + "Found " + countOfOfficials + " political officials from " + countOfOffices + " public offices " + "+++++++");
		setContactProfile(politicalContactProfileString);
	}

	public StringBuilder appendToContactString(String contactElement) {
		politicalContactProfileString.append(contactElement).append("\n");
		return politicalContactProfileString;
	}

	public void setContactProfile(StringBuilder politicalContactProfile) throws Exception{
		PoliticalContactProfile polProfile = new PoliticalContactProfile();
		polProfile.setContactProfile(politicalContactProfile);
		System.out.println(polProfile.getContactProfile());
	}
}

package dev.raycool.polaction;

import dev.raycool.polaction.view.PoliticalContactProfile;
import dev.raycool.polaction.officesresponsemodels.PoliticalOffice;
import dev.raycool.polaction.officialsresponsemodels.*;
import dev.raycool.polaction.service.PoliticalOfficialsResponse;
import dev.raycool.polaction.view.HtmlLineFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@SpringBootApplication
@RestController
public class PolActionApplication  {

	private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);
	StringBuilder politicalContactProfileString = new StringBuilder("\n");
	PoliticalContactProfile polProfile = new PoliticalContactProfile();
	HtmlLineFormatter htmlLineFormatter = new HtmlLineFormatter();

	@Value("${apikey}")
	private String googleApiKey;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate(){ return new RestTemplate(); }

	public static void main(String[] args) {
		SpringApplication.run(PolActionApplication.class, args);
	}

	@RequestMapping(value = "/default")
	public String getData() throws Exception {
		politicalContactProfileString.setLength(0);
		consumeGoogleApi("19977");
		return polProfile.getContactProfile().toString();
	}

	@RequestMapping(value = "/api")
	public String getData(@RequestParam String location) throws Exception {
		politicalContactProfileString.setLength(0);
		consumeGoogleApi(location);
		System.out.println(polProfile.getContactProfile().length());
		return polProfile.getContactProfile().toString();
	}

	public void consumeGoogleApi(String locationToSearch) throws Exception {

		int countOfOfficials = 0;
		int countOfOffices = 0;
		int countOfOfficialsPerOffice = 0;

		String formattedGoogleApiUrl = String.format("https://www.googleapis.com/civicinfo/v2/representatives/?&address=%s&includeOffices=true&key=%s", locationToSearch, googleApiKey);

		ResponseEntity<PoliticalOfficialsResponse> response = restTemplate.getForEntity(formattedGoogleApiUrl, PoliticalOfficialsResponse.class);
		PoliticalOfficial[] allOfficials = Objects.requireNonNull(response.getBody()).getOfficials();
		PoliticalOffice[] allOffices = Objects.requireNonNull(response.getBody()).getOffices();
		String locationData = response.getBody().getNormalizedInput().toString();

		String htmlHead = "<head>\n" +
				"  <meta charset=\"UTF-8\">\n" +
				"  <meta name=\"description\" content=\"Political Reps By Location\">\n" +
				"  <meta name=\"keywords\" content=\"HTML, CSS, JavaScript\">\n" +
				"  <meta name=\"author\" content=\"Raymond Cool\">\n" +
				"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
				"</head>";

		logger.info(locationData);
		appendToContactString(htmlHead);
		appendToContactString("<br>" + locationData );

		for(PoliticalOffice politicalOffice : allOffices) {
			countOfOffices += 1;
			countOfOfficialsPerOffice = 0;
			String currentOffice = politicalOffice.getName();
			String formattedOffice = htmlLineFormatter.formatAsH2Header(currentOffice);
			logger.info(formattedOffice);
			appendToContactString("<br>" + formattedOffice);

			for (int i = 0; i < politicalOffice.getLevels().length; i++) {
				logger.info(politicalOffice.getLevels()[i].toString());
			}

			if (politicalOffice.getRoles() != null) {
				//appendToContactString("API Roles:"); //optional
				for (int i = 0; i < politicalOffice.getRoles().length; i++) {
					//appendToContactString(politicalOffice.getRoles()[i].toString()); //optional
					logger.info(politicalOffice.getRoles()[i].getRole());
				}
			}

			/**
			 * For the current political office loop exactly the number of times as the length of the list of indexes
			 *
			 */
			for (int i = 0; i < politicalOffice.getOfficialIndices().length; i++ ) {

				/**
				 * Get the political official who's name appears at the current index number in the Officials array
				 */
				PoliticalOfficial politicalOfficial = allOfficials[politicalOffice.getOfficialIndices()[i].getOfficialIndex()];
				countOfOfficials += 1;
				countOfOfficialsPerOffice +=1;
				String currentOfficial = politicalOfficial.getName();
				String formattedOfficialName = htmlLineFormatter.formatAsH3Header(currentOfficial);
				logger.info(formattedOfficialName);
				appendToContactString(formattedOfficialName);
				logger.info(politicalOfficial.toString());
				//appendToContactString(politicalOfficial.toString());

				if (politicalOfficial.getParty() != null) {
					String currentAffiliation = politicalOfficial.getParty();
					String formattedAffiliation = htmlLineFormatter.formatAsParagraph(currentAffiliation);
					logger.info(formattedAffiliation);
					appendToContactString(formattedAffiliation);
				}

				if (politicalOfficial.getPhotoUrl() != null) {
					String currentPhotoUrl = politicalOfficial.getPhotoUrl();
					String imageUrl = htmlLineFormatter.formatAsImage(currentPhotoUrl);
					logger.info("Photo: " + imageUrl);
					appendToContactString(imageUrl + "<br>");
				}

				if (politicalOfficial.getAddress() != null) {
					for (Address address : politicalOfficial.getAddress()) {
						String currentAddress = address.toString();
						String formattedAddress = htmlLineFormatter.formatAsPhysicalAddress(currentAddress);
						logger.info(formattedAddress);
						appendToContactString(formattedAddress);
					}
				}

				if (politicalOfficial.getEmails() != null) {
					for (Email emailAddress : politicalOfficial.getEmails()) {
						String currentEmailAddress = emailAddress.getEmail();
						String formattedEmailAddress = htmlLineFormatter.formatAsEmailAddress(currentEmailAddress);
						logger.info(formattedEmailAddress);
						appendToContactString(formattedEmailAddress);
					}
				}

				if (politicalOfficial.getPhones() != null) {
					//appendToContactString("Phones: ");
					for (Phone phoneNumber : politicalOfficial.getPhones()) {
						String currentPhoneNumber = phoneNumber.getPhone();
						currentPhoneNumber = currentPhoneNumber.replace("-"," ");
						String formattedPhoneNumber = htmlLineFormatter.formatAsTelephoneNumber(currentPhoneNumber);
						logger.info(formattedPhoneNumber);
						appendToContactString(formattedPhoneNumber);
					}

				if (politicalOfficial.getUrls() != null) {
					for (Url url : politicalOfficial.getUrls()) {
						String currentUrl = url.getUrl();
						String websiteLink = htmlLineFormatter.formatAsLink(currentUrl);
						logger.info(websiteLink);
						appendToContactString(websiteLink);
					}
				}

				if (politicalOfficial.getChannels() != null) {
					for (Channel channel : politicalOfficial.getChannels()) {
						String currentChannel = channel.getType() + " " + channel.getId();
						String formattedChannel = htmlLineFormatter.formatAsParagraph(currentChannel);
						logger.info(formattedChannel);
						appendToContactString(formattedChannel);
						}
					}

				}

			}
			appendToContactString(htmlLineFormatter.formatAsParagraph("Number of officials found for the office of " + politicalOffice.getName() + " = " + countOfOfficialsPerOffice));
		}
		setContactProfile(politicalContactProfileString);
	}

	public StringBuilder appendToContactString(String contactElement) {
		politicalContactProfileString.append(contactElement);
		return politicalContactProfileString;
	}

	public void setContactProfile(StringBuilder politicalContactProfile) throws Exception{
		polProfile.setContactProfile(politicalContactProfile);
		logger.info(polProfile.getContactProfile().toString());
		//System.out.println(polProfile.getContactProfile());
	}
}

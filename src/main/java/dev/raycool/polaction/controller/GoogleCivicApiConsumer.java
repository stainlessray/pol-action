package dev.raycool.polaction.controller;

import dev.raycool.polaction.PolActionApplication;
import dev.raycool.polaction.officesresponsemodels.PoliticalOffice;
import dev.raycool.polaction.officialsresponsemodels.*;
import dev.raycool.polaction.service.PoliticalOfficialsResponse;
import dev.raycool.polaction.view.PoliticalContactProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
public class GoogleCivicApiConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);
    StringBuilder politicalContactProfileString = new StringBuilder("\n");
    PoliticalContactProfile polProfile = new PoliticalContactProfile();

    @Value("${apikey}")
    private String googleApiKey;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() { return new RestTemplate(); }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String getData() throws Exception {
        politicalContactProfileString.setLength(0);
        consumeGoogleApi("19977");
        return polProfile.getContactProfile().toString();
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
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

        String htmlHead = "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"description\" content=\"Political Reps By Location\">\n" +
                "  <meta name=\"keywords\" content=\"HTML, CSS\">\n" +
                "  <meta name=\"author\" content=\"Raymond Cool\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "</head>";

        ResponseEntity<PoliticalOfficialsResponse> response = restTemplate.getForEntity(formattedGoogleApiUrl, PoliticalOfficialsResponse.class);
        PoliticalOfficial[] allOfficials = Objects.requireNonNull(response.getBody()).getOfficials();
        PoliticalOffice[] allOffices = Objects.requireNonNull(response.getBody()).getOffices();
        String locationData = response.getBody().getNormalizedInput().toString();

        logger.info(locationData);
        appendToContactString(htmlHead);
        appendToContactString("<br>" + locationData );

        for(PoliticalOffice politicalOffice : allOffices) {
            countOfOffices += 1;
            countOfOfficialsPerOffice = 0;
            String currentOffice = politicalOffice.getName();
            logger.info(currentOffice);
            appendToContactString(currentOffice);

            for (int i = 0; i < politicalOffice.getLevels().length; i++) {
                logger.info(politicalOffice.getLevels()[i].toString());
            }

            if (politicalOffice.getRoles() != null) {
                for (int i = 0; i < politicalOffice.getRoles().length; i++) {
                    logger.info(politicalOffice.getRoles()[i].getRole());
                }
            }

            for (int i = 0; i < politicalOffice.getOfficialIndices().length; i++ ) {

                PoliticalOfficial politicalOfficial = allOfficials[politicalOffice.getOfficialIndices()[i].getOfficialIndex()];
                countOfOfficials += 1;
                countOfOfficialsPerOffice +=1;

                String currentOfficial = politicalOfficial.getName();

                logger.info(currentOfficial);
                appendToContactString(currentOfficial);

                if (politicalOfficial.getParty() != null) {
                    String currentAffiliation = politicalOfficial.getParty();
                    logger.info(currentAffiliation);
                    appendToContactString(currentAffiliation);
                }

                if (politicalOfficial.getPhotoUrl() != null) {
                    String currentPhotoUrl = politicalOfficial.getPhotoUrl();
                    logger.info("Photo: " + currentPhotoUrl);
                    appendToContactString(currentPhotoUrl);
                }

                if (politicalOfficial.getAddress() != null) {
                    for (Address address : politicalOfficial.getAddress()) {
                        String currentAddress = address.toString();
                        logger.info(currentAddress);
                        appendToContactString(currentAddress);
                    }
                }

                if (politicalOfficial.getEmails() != null) {
                    for (Email emailAddress : politicalOfficial.getEmails()) {
                        String currentEmailAddress = emailAddress.getEmail();
                        logger.info(currentEmailAddress);
                        appendToContactString(currentEmailAddress);
                    }
                }

                if (politicalOfficial.getPhones() != null) {
                    for (Phone phoneNumber : politicalOfficial.getPhones()) {
                        String currentPhoneNumber = phoneNumber.getPhone();
                        currentPhoneNumber.replace("-"," ");
                        logger.info(currentPhoneNumber);
                        appendToContactString(currentPhoneNumber);
                    }

                    if (politicalOfficial.getUrls() != null) {
                        for (Url url : politicalOfficial.getUrls()) {
                            String currentUrl = url.getUrl();
                            logger.info(currentUrl);
                            appendToContactString(currentUrl);
                        }
                    }

                    if (politicalOfficial.getChannels() != null) {
                        for (Channel channel : politicalOfficial.getChannels()) {
                            String currentChannel = channel.getType() + " " + channel.getId();
                            logger.info(currentChannel);
                            appendToContactString(currentChannel);
                        }
                    }

                }

            }
            appendToContactString("Number of officials found for the office of " + politicalOffice.getName() + " = " + countOfOfficialsPerOffice);
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
    }
}

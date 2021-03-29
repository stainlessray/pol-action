package dev.raycool.polaction;

import dev.raycool.polaction.officesresponsemodels.PoliticalOffice;
import dev.raycool.polaction.officialsresponsemodels.PoliticalOfficial;
import dev.raycool.polaction.service.PoliticalOfficialsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Controller
public class GoogleCivicApiConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);
    StringBuilder politicalContactProfileString = new StringBuilder("\n");

    @Value("${apikeyB}")
    private String googleApiKeyB;

    @Autowired
    RestTemplate restTemplateB;

    @Bean
    public RestTemplate restTemplateB() {
        return new RestTemplate();
    }


    public void consumeApi(String... args) throws Exception {

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

        String formattedGoogleApiUrl = String.format("https://www.googleapis.com/civicinfo/v2/representatives/?&address=%s&includeOffices=true&key=%s", coloradoAirport, "AIzaSyB-tUdCgR_eX1pDG2E2xIVDTqPZzto90ac");

        ResponseEntity<PoliticalOfficialsResponse> response = restTemplateB.getForEntity(formattedGoogleApiUrl, PoliticalOfficialsResponse.class);
        PoliticalOfficial[] allOfficials = Objects.requireNonNull(response.getBody()).getOfficials();
        PoliticalOffice[] allOffices = Objects.requireNonNull(response.getBody()).getOffices();
        String locationData = response.getBody().getNormalizedInput().toString();
        System.out.println(locationData);
    }
}

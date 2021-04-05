package dev.raycool.polaction.controller;

import dev.raycool.polaction.PolActionApplication;
import dev.raycool.polaction.model.Location;
import dev.raycool.polaction.model.PoliticalOfficialsResponse;
import dev.raycool.polaction.model.PublicOffice;
import dev.raycool.polaction.officesresponse.NormalizedInput;
import dev.raycool.polaction.officesresponse.PoliticalOffice;
import dev.raycool.polaction.officialsresponse.PoliticalOfficial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
public class GoogleCivicApiController {

    private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);
    Location location;
    PublicOffice publicOffice;
    Set<String> locationHistory = new HashSet<>();

    @Value("${apikey}")
    private String googleApiKey;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() { return new RestTemplate(); }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String getData(@RequestParam String location, Model model) throws Exception {

        if (locationHistory == null) {
            locationHistory.add(location);
        }
        if (locationHistory.contains(location)) {
            String locationData = this.location.getSearchLocation().toString();
            model.addAttribute("locationData", locationData);

            List<PublicOffice> offices = this.location.getOffices();
            model.addAttribute("offices", offices);
            logger.info("outputting to page");
            return "publicresponse";
        } else {
        locationHistory.add(location);
        logger.info("making API request");
        consumeGoogleApi(location);

        String locationData = this.location.getSearchLocation().toString();
        model.addAttribute("locationData", locationData);

        List<PublicOffice> offices = this.location.getOffices();
        model.addAttribute("offices", offices);
        logger.info("outputting to page");}
        return "publicresponse";
    }

    public void consumeGoogleApi(String locationToSearch) throws Exception {
        location = null;
        location = new Location();
        int countOfOfficials = 0;
        int countOfOffices = 0;
        int countInThisOffice = 0;

        String googleCivicApiUrl = String.format("https://www.googleapis.com/civicinfo/v2/representatives/?&address=%s&includeOffices=true&key=%s", locationToSearch, googleApiKey);
        ResponseEntity<PoliticalOfficialsResponse> response = restTemplate.getForEntity(googleCivicApiUrl, PoliticalOfficialsResponse.class);

        PoliticalOfficial[] allOfficials = Objects.requireNonNull(response.getBody()).getOfficials();
        PoliticalOffice[] allOffices = Objects.requireNonNull(response.getBody()).getOffices();

        NormalizedInput locationData = response.getBody().getNormalizedInput();
        location.setSearchLocation(locationData);

        for(PoliticalOffice politicalOffice : allOffices) {
            publicOffice = new PublicOffice();
            countOfOffices += 1;
            countInThisOffice = 0;
            String currentOffice = politicalOffice.getName();
            publicOffice.setOfficeName(currentOffice);

            for (int i = 0; i < politicalOffice.getLevels().length; i++) {
                publicOffice.addLevel(politicalOffice.getLevels()[i]);
            }

            if (politicalOffice.getRoles() != null) {
                for (int i = 0; i < politicalOffice.getRoles().length; i++) {
                    publicOffice.addRole(politicalOffice.getRoles()[i]);
                }
            }

            for (int i = 0; i < politicalOffice.getOfficialIndices().length; i++ ) {
                PoliticalOfficial politicalOfficial = allOfficials[politicalOffice.getOfficialIndices()[i].getOfficialIndex()];
                publicOffice.addOfficial(politicalOfficial);
                countOfOfficials += 1;
                countInThisOffice +=1;
                publicOffice.setCountInThisOffice(countInThisOffice);
            }

            location.setCountOfOfficials(countOfOfficials);
            location.setCountOfOffices(countOfOffices);
            location.addOffice(publicOffice);
        }
    }
}

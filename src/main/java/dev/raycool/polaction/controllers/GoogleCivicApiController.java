package dev.raycool.polaction.controllers;

import dev.raycool.polaction.PolActionApplication;
import dev.raycool.polaction.api.shared.dto.LocationDto;
import dev.raycool.polaction.api.shared.dto.PoliticalOfficialsDto;
import dev.raycool.polaction.api.shared.dto.PublicOfficeDto;
import dev.raycool.polaction.api.offices.model.response.NormalizedInput;
import dev.raycool.polaction.api.offices.model.response.PoliticalOffice;
import dev.raycool.polaction.api.officials.model.response.PoliticalOfficial;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
public class GoogleCivicApiController {
    private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);
    private LocationDto locationDto;
    private Set<String> sessionSearchHistory = new HashSet<>();
    private static Map<Integer, String> sessionOfficialHistory = new HashMap<>();
    private List<LocationDto> savedLocationDtos = new ArrayList<>();
    private String locationData;
    private String locationAggregated;

    @Value("${apikey}")
    private String googleApiKey;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * @param lookup static object created for search session
     * @param model  the model for the template
     * @return model to template
     * @throws HttpClientErrorException invalid search results throw exception
     */
    //@CrossOrigin(origins = "http://polaction-env.eba-e2zxwwme.us-east-2.elasticbeanstalk.com:5000")
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String getData(@RequestParam String lookup, Model model) throws HttpClientErrorException {

        if ( sessionSearchHistory == null ) {
            sessionSearchHistory.add(lookup);
        }
        if ( sessionSearchHistory.contains(lookup) ) {
            createModel(model);
            logger.info("outputting to page");
            return "publicresponse";
        } else {
            sessionSearchHistory.add(lookup);
            logger.info("making API request");
            consumeGoogleCivicApi(lookup);
            createModel(model);
            logger.info("outputting to page");
        }
        return "publicresponse";
    }

    @RequestMapping(value = "/delete")
    public String removeData(@RequestParam String delete) {
        startNewSession();
        return "redirect:/";
    }

    public Model createModel(Model model) {
        locationData = this.locationDto.getSearchLocation().toString();
        locationAggregated = sessionSearchHistory.toString().replace("[", "").replace("]", "");
        List<PublicOfficeDto> offices = this.locationDto.getOffices();

        model.addAttribute("locationData", locationData);
        model.addAttribute("locationAggregated", locationAggregated);
        model.addAttribute("offices", offices);
        return model;
    }

    public void startNewSession() {
        logger.info("cleaning...");
        if ( locationDto != null ) {
            sessionSearchHistory.clear();
            sessionOfficialHistory.clear();
            locationDto.clearAll();
            locationData = "";
            locationAggregated = "";
        }
    }

    public void consumeGoogleCivicApi(String locationToSearch) throws HttpClientErrorException {
        locationDto = new LocationDto();

        try {
            String googleCivicApiUrl = String.format("https://www.googleapis.com/civicinfo/v2/representatives/?&address=%s&includeOffices=true&key=%s", locationToSearch, googleApiKey);
            ResponseEntity<PoliticalOfficialsDto> response = restTemplate.getForEntity(googleCivicApiUrl, PoliticalOfficialsDto.class);

            PoliticalOfficial[] allOfficials = Objects.requireNonNull(response.getBody()).getOfficials();
            PoliticalOffice[] allOffices = Objects.requireNonNull(response.getBody()).getOffices();

            NormalizedInput locationData = response.getBody().getNormalizedInput();
            locationDto.setSearchLocation(locationData);

            parseApiResponse(allOfficials, allOffices);
        } catch (Exception e) {
            logger.info("There was an error when trying to query the API. See the trace for the issue stack", e);
        }
    }

    private void parseApiResponse(PoliticalOfficial[] allOfficials, PoliticalOffice[] allOffices) {
        int countOfOfficials = 0;
        int countOfOffices = 0;
        int countInThisOffice = 0;

        for (PoliticalOffice politicalOffice : allOffices) {
            PublicOfficeDto publicOfficeDto = new PublicOfficeDto();
            countOfOffices += 1;
            countInThisOffice = 0;
            String currentOffice = politicalOffice.getName();
            publicOfficeDto.setOfficeName(currentOffice);

            for (int i = 0; i < politicalOffice.getLevels().length; i++) {
                publicOfficeDto.addLevel(politicalOffice.getLevels()[i]);
            }

            if ( politicalOffice.getRoles() != null ) {
                for (int i = 0; i < politicalOffice.getRoles().length; i++) {
                    publicOfficeDto.addRole(politicalOffice.getRoles()[i]);
                }
            }

            for (int i = 0; i < politicalOffice.getOfficialIndices().length; i++) {
                PoliticalOfficial politicalOfficial = allOfficials[politicalOffice.getOfficialIndices()[i].getOfficialIndex()];
                if ( !sessionOfficialHistory.containsKey(politicalOfficial.hashCode()) ) {
                    sessionOfficialHistory.put(politicalOfficial.hashCode(), politicalOfficial.getName());
                    publicOfficeDto.addOfficial(politicalOfficial);
                    countOfOfficials += 1;
                    countInThisOffice += 1;
                    publicOfficeDto.setCountInThisOffice(countInThisOffice);
                } else {
                    logger.info("Already present in official history");
                }
            }

            locationDto.setCountOfOfficials(countOfOfficials);
            locationDto.setCountOfOffices(countOfOffices);
            if ( publicOfficeDto.getOfficials().size() > 0 ) {
                locationDto.addOffice(publicOfficeDto);
            }
        }
        savedLocationDtos.add(locationDto);
        logger.info(locationDto.toString());
    }
}

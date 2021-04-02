package dev.raycool.polaction;

import dev.raycool.polaction.officesmodels.PoliticalOffice;
import dev.raycool.polaction.officialsmodels.*;
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



	public static void main(String[] args) {
		SpringApplication.run(PolActionApplication.class, args);
	}


}

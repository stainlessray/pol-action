package dev.raycool.polaction.officialsresponsemodels;

import dev.raycool.polaction.view.HtmlLineFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PoliticalOfficialTest {
    PoliticalOfficial politicalOfficial;

    @BeforeEach
    void setUp() {
        politicalOfficial = new PoliticalOfficial();
        politicalOfficial.setName("Bill Politician");
        Phone phone1 = new Phone("(302) 555 1111");
        Phone phone2 = new Phone("(302) 555 2222");
        Phone[] phones = new Phone[2];
        phones[0] = phone1;
        phones[1] = phone2;
        politicalOfficial.setPhones(phones);
        politicalOfficial.setParty("Non Partisan");
        politicalOfficial.setPhotoUrl("http://bioguide.congress.gov/bioguide/photo/C/C000174.jpg");
    }

    @Test
    void getPoliticalOfficialToString() {
        HtmlLineFormatter htmlLineFormatter = new HtmlLineFormatter();
        //System.out.println(politicalOfficial.phonesToString());
        //System.out.println(htmlLineFormatter.formatAsTelephoneNumber(politicalOfficial.phonesToString()));
        System.out.println(politicalOfficial.getPhonesToString());
    }
}
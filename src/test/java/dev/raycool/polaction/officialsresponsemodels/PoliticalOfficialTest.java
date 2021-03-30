package dev.raycool.polaction.officialsresponsemodels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PoliticalOfficialTest {

    @Test
    void getAllPhonesAsList() {
       PoliticalOfficial politicalOfficial = new PoliticalOfficial();
       Phone phone1 = new Phone("(302) 555 1111");
       Phone phone2 = new Phone("(302) 555 2222");
       Phone[] phones = new Phone[2];
       phones[0] = phone1;
       phones[1] = phone2;
       politicalOfficial.setPhones(phones);
       System.out.println(politicalOfficial.getAllPhonesAsList());
    }
}
package dev.raycool.polaction.controller;

import dev.raycool.polaction.model.PoliticalOfficial;
import dev.raycool.polaction.service.PoliticalOfficialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PoliticalOfficialController {

    @Autowired
    private PoliticalOfficialService politicalOfficialService;

    @GetMapping("/all")
    public ResponseEntity<List<PoliticalOfficial>> getAllOfficial() {
        return ResponseEntity.ok().body(PoliticalOfficialService.getAllPoliticalOfficial());
    }
}

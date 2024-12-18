package org.example.est_team_project2.api.pedia;

import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.dto.pedia.PediaDTO;
import org.example.est_team_project2.service.pedia.PediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedia")
public class PediaController {

    private final org.example.est_team_project2.service.pedia.PediaService service;

    @Autowired
    public PediaController(PediaService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Pedia> registerPedia(@RequestBody PediaDTO pediaDTO) {
        Pedia pedia = new Pedia();
        pedia.setMemberId(pediaDTO.getMemberId());
//        pedia.setPediaContentId(pediaDTO.getPediaContentId());
//        pedia.setPediaVersion(pediaDTO.getPediaVersion());
        Pedia savedPedia = service.save(pedia);
        return ResponseEntity.ok(savedPedia);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pedia>> getAllPedia() {
        List<Pedia> pediaList = service.findAll();
        return ResponseEntity.ok(pediaList);
    }
}
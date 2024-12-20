package org.example.est_team_project2.api.pedia;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedia")
public class PediaController {

//    private final org.example.est_team_project2.service.pedia.PediaService service;
//
//    @Autowired
//    public PediaController(PediaService service) {
//        this.service = service;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<Pedia> registerPedia(@RequestBody PediaDto pediaDTO) {
//        Pedia pedia = new Pedia();
//        pedia.setMemberId(pediaDTO.getMemberId());
////        pedia.setPediaContentId(pediaDTO.getPediaContentId());
////        pedia.setPediaVersion(pediaDTO.getPediaVersion());
//        Pedia savedPedia = service.save(pedia);
//        return ResponseEntity.ok(savedPedia);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Pedia>> getAllPedia() {
//        List<Pedia> pediaList = service.findAll();
//        return ResponseEntity.ok(pediaList);
//    }
}
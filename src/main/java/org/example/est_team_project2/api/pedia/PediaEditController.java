package org.example.est_team_project2.api.pedia;


import org.example.est_team_project2.domain.pedia.PediaEdit;
import org.example.est_team_project2.dto.pedia.PediaEditDTO;
import org.example.est_team_project2.service.pedia.PediaEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pediaedit")
public class PediaEditController {

    private final PediaEditService pediaEditService; // PediaEditService를 주입

    @Autowired
    public PediaEditController(PediaEditService pediaEditService) {
        this.pediaEditService = pediaEditService; // 주입된 서비스로 초기화
    }

    @PostMapping("/request")
    public ResponseEntity<PediaEdit> request(@RequestBody PediaEditDTO pediaEditDTO) {
        PediaEdit pediaEdit = new PediaEdit();
        pediaEdit.setPediaContentId(pediaEditDTO.getPediaContentId());
        pediaEdit.setMemberId(pediaEditDTO.getMemberId());
        pediaEdit.setPediaVersion(pediaEditDTO.getPediaVersion());
        pediaEdit.setStatus("Requested");

        PediaEdit savePediaEdit = pediaEditService.save(pediaEdit);
        return ResponseEntity.ok(savePediaEdit);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PediaEditDTO>> getAllPediaEdits() {
        List<PediaEdit> pediaEditList = pediaEditService.findAll();

        List<PediaEditDTO> pediaEditDTOList = pediaEditList.stream()
                .map(pediaEdit -> new PediaEditDTO(
                        pediaEdit.getPediaEditRequestId(),
                        pediaEdit.getPediaContentId(),
                        pediaEdit.getMemberId(),
                        pediaEdit.getPediaVersion(),
                        pediaEdit.getStatus()
                ))
                .toList();
        return ResponseEntity.ok(pediaEditDTOList);

    }

}
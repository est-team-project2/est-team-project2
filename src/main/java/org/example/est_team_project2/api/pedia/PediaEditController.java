package org.example.est_team_project2.api.pedia;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pediaedit")
public class PediaEditController {

//    private final PediaEditService pediaEditService; // PediaEditService를 주입
//
//    @Autowired
//    public PediaEditController(PediaEditService pediaEditService) {
//        this.pediaEditService = pediaEditService; // 주입된 서비스로 초기화
//    }
//
//    @PostMapping("/request")
//    public ResponseEntity<PediaEdit> request(@RequestBody PediaEditDTO pediaEditDTO) {
//        PediaEdit pediaEdit = new PediaEdit();
//        pediaEdit.setPediaContentId(pediaEditDTO.getPediaContentId());
//        pediaEdit.setMemberId(pediaEditDTO.getMemberId());
//        pediaEdit.setPediaVersion(pediaEditDTO.getPediaVersion());
//        pediaEdit.setStatus("Requested");
//
//        PediaEdit savePediaEdit = pediaEditService.save(pediaEdit);
//        return ResponseEntity.ok(savePediaEdit);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<PediaEditDTO>> getAllPediaEdits() {
//        List<PediaEdit> pediaEditList = pediaEditService.findAll();
//
//        List<PediaEditDTO> pediaEditDTOList = pediaEditList.stream()
//                .map(pediaEdit -> new PediaEditDTO(
//                        pediaEdit.getPediaEditRequestId(),
//                        pediaEdit.getPediaContentId(),
//                        pediaEdit.getMemberId(),
//                        pediaEdit.getPediaVersion(),
//                        pediaEdit.getStatus()
//                ))
//                .toList();
//        return ResponseEntity.ok(pediaEditDTOList);
//
//    }

}
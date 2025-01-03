package org.example.est_team_project2.service.pedia;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.pedia.PediaContentRepository;
import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;
import org.example.est_team_project2.dto.pedia.PediaContentDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor//final이 붙은 필드의 생성자를 만들어준다.
@Transactional
public class PediaContentService {

    private final PediaContentRepository pediaContentRepository;


    public void save(PediaContentDto pediaContentDTO) {
        PediaContent pediaContent = PediaContent.builder().imageUri(pediaContentDTO.getImageUri())
            .breed(pediaContentDTO.getBreed()).origin(pediaContentDTO.getOrigin())
            .size(pediaContentDTO.getSize()).detail(pediaContentDTO.getDetail())
            .geneticDisease(pediaContentDTO.getGeneticDisease())
            .feature(pediaContentDTO.getFeature()).build();
        pediaContentRepository.save(pediaContent);
    }


    //    경돈님꺼
    public PediaContent saveEdit(PediaContentDto pediaContentDto) {
        return pediaContentRepository.save(PediaContent.from(pediaContentDto));
    }


    public void registerOnlySaveBreed(PediaContentDto pediaContentDTO) {

        PediaContent pediaContent = PediaContent.builder()
                .imageUri("/uploads/baseImg.png")
                .breed(pediaContentDTO.getBreed())
                .origin("원산지를 추가해주세요")
                .size("크기를 추가해주세요")
                .detail("세부 정보를 추가해주세요")
                .geneticDisease("유전병을 추가해주세요")
                .feature("특징을 추가해주세요")
                .status(CommonStatus.ACTIVE)
                .build();

        pediaContentRepository.save(pediaContent);
    }

    public List<PediaContent> findAll() {
        return pediaContentRepository.findAll();
    }


    public PediaContent findById(Long id) {

        return pediaContentRepository.findById(id).orElseThrow();
    }

//
//    public List<PediaContentDto> findTop8ByStatusOrderByIdDesc() {
//
//
//
//        Pageable pageable = PageRequest.of(0, 8);
//
//
//
//        return pediaContentRepository.findTop8ByStatusOrderByIdDesc(pageable)
//                .stream()
//                .map(PediaContentDto::from)
//                .toList();
//    }





//    public PediaContent findByBeforeInfo(Long id) {
//        PediaContent EditPediaContent = pediaContentRepository.findById(id).orElseThrow();
//        Long Editid = EditPediaContent.getId(); //7
//        String EditBreed = EditPediaContent.getBreed(); //진돗개
//
//        List<PediaContent> all = pediaContentRepository.findAll();
//
//
//    }

}
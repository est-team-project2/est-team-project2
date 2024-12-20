package org.example.est_team_project2.service.pedia;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.pedia.PediaContentRepository;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;
import org.example.est_team_project2.dto.pedia.PediaContentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor//final이 붙은 필드의 생성자를 만들어준다.
@Transactional
public class PediaContentService {

    private final PediaContentRepository pediaContentRepository;



//    public void save(PediaContentDto pediaContentDTO) {
//        PediaContent pediaContent = PediaContent.builder()
//                .imageUri(pediaContentDTO.getImageUri())
//                .breed(pediaContentDTO.getBreed())
//                .origin(pediaContentDTO.getOrigin())
//                .size(pediaContentDTO.getSize())
//                .detail(pediaContentDTO.getDetail())
//                .geneticDisease(pediaContentDTO.getGeneticDisease())
//                .feature(pediaContentDTO.getFeature())
//                .build();
//        pediaContentRepository.save(pediaContent);
//    }

    //경돈님꺼
    public PediaContent save(PediaContentDto pediaContentDto) {
        return pediaContentRepository.save(PediaContent.from(pediaContentDto));
    }


    public void registerOnlyBreed(PediaContentDto pediaContentDTO) {
        PediaContent pediaContent = PediaContent.builder()
                .imageUri("방금 만든 따끈따끈한 견종입니다 이미지를 추가해주세요")
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


}
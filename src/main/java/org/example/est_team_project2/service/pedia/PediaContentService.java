package org.example.est_team_project2.service.pedia;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.pedia.PediaContentRepository;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.dto.pedia.PediaContentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PediaContentService {

    private final PediaContentRepository pediaContentRepository;

    public PediaContent save(PediaContentDto pediaContentDto) {
        return pediaContentRepository.save(PediaContent.from(pediaContentDto));
    }
}

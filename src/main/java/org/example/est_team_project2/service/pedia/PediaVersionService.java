package org.example.est_team_project2.service.pedia;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.pedia.PediaVersionRepository;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.example.est_team_project2.dto.pedia.PediaVersionDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PediaVersionService {

    private final PediaVersionRepository pediaVersionRepository;

    public PediaVersionDto save(PediaVersionDto pediaVersionDto) {
        return PediaVersionDto.from(
            pediaVersionRepository.save(PediaVersion.from(pediaVersionDto)));
    }
}

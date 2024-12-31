package org.example.est_team_project2.service.pedia;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.pedia.PediaVersionRepository;
import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PediaVersionService {

    private final PediaVersionRepository pediaVersionRepository;

    public PediaVersion save(PediaVersion pediaVersion) {
        return pediaVersionRepository.save(pediaVersion);
    }

    public PediaVersion getPediaVersionByCode(String code) {
        return pediaVersionRepository.findByPediaVersionCode(code).orElseThrow(
            () -> new NoSuchElementException("Pedia Version By Code Not Found")
        );
    }


    public List<PediaVersion> findAll() {
        return pediaVersionRepository.findAll();
    }


    //버전 서비스
    public List<PediaVersion> findByPediaId(Long id) {
       return pediaVersionRepository.findByPediaId(id);
    }




}

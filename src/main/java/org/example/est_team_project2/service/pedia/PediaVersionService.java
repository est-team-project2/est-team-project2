package org.example.est_team_project2.service.pedia;

import org.example.est_team_project2.dao.pedia.PediaVersionRepository;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PediaVersionService {

    @Autowired
    private PediaVersionRepository pediaVersionRepository;

    public PediaVersion save(PediaVersion pediaVersion) {
        return pediaVersionRepository.save(pediaVersion);
    }
}

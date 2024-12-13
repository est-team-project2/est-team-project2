package org.example.est_team_project2.service;

import org.example.est_team_project2.dao.PediaContentRepository;
import org.example.est_team_project2.domain.PediaContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PediaContentService {
    private final PediaContentRepository pediaContentRepository;

    @Autowired
    public PediaContentService(PediaContentRepository pediaContentRepository) {
        this.pediaContentRepository = pediaContentRepository;

    }

    public PediaContent savePediaContent(PediaContent pediaContent) {
        return pediaContentRepository.save(pediaContent);
    }

    public List<PediaContent> getAllPediaContent() {
        return pediaContentRepository.findAll();
    }
}
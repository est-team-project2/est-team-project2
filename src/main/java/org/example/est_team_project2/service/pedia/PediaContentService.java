package org.example.est_team_project2.service.pedia;


import org.example.est_team_project2.dao.pedia.PediaContentRepository;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PediaContentService {

    private final PediaContentRepository repository;

    @Autowired
    public PediaContentService(PediaContentRepository repository) {
        this.repository = repository;
    }

    public PediaContent save(PediaContent pediaContent) {
        // PediaContent 저장
        return repository.save(pediaContent);
    }

    public List<PediaContent> findByBreed(String breed) {
        return repository.findByBreedContaining(breed);
    }
}
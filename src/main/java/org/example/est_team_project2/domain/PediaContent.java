package org.example.est_team_project2.domain;

import org.example.domain.PediaContent;
import org.example.est_team_project2.dao.PediaContentRepository;
import org.example.repository.PediaContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PediaContentService {

    private final PediaContent repository;

    @Autowired
    public PediaContentService(PediaContentRepository repository) {
        this.repository = repository;
    }

    public List<PediaContent> findAll() {
        return repository.findAll();
    }

    public PediaContent save(PediaContent pediaContent) {
        return repository.save(pediaContent);
    }
}
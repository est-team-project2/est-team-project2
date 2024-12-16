package org.example.est_team_project2.service;


import org.example.est_team_project2.dao.PediaRepository;
import org.example.est_team_project2.domain.Pedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PediaService {

    private final PediaRepository repository;

    @Autowired
    public PediaService(PediaRepository repository) {
        this.repository = repository;

    }

    public List<Pedia> findAll() {
        return repository.findAll();
    }

    public Pedia save(Pedia pedia) {
        return repository.save(pedia);
    }

}

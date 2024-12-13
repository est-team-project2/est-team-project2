package org.example.est_team_project2.service;

import org.example.est_team_project2.dao.PediaRepository;
import org.example.est_team_project2.domain.Pedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PediaService {
    private PediaRepository pediaRepository;

    @Autowired
    public PediaService(PediaRepository pediaRepository) {
        this.pediaRepository = pediaRepository;

    }

    public Pedia savePedia(Pedia pedia) {
        return pediaRepository.save(pedia);
    }

    public List<Pedia> getAllPedia() {
        return pediaRepository.findAll();
    }
}


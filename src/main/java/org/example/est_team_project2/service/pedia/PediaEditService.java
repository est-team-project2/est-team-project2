package org.example.est_team_project2.service.pedia;


import org.example.est_team_project2.dao.pedia.PediaEditRepository;
import org.example.est_team_project2.domain.pedia.requestEnums.PediaEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PediaEditService {

    private final PediaEditRepository repository;

    @Autowired
    public PediaEditService(PediaEditRepository repository) {
        this.repository = repository;
    }

    public PediaEdit save(PediaEdit pediaEdit) {
        return repository.save(pediaEdit);
    }

    public List<PediaEdit> findAll() {
        return repository.findAll();
    }
}

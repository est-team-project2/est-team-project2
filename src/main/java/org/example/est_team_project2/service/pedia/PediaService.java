package org.example.est_team_project2.service.pedia;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.pedia.PediaRepository;
import org.example.est_team_project2.domain.pedia.Pedia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PediaService {

    private final PediaRepository pediaRepository;

    public void save(String breed) {
        Pedia pedia = Pedia.builder()
                .title(breed)
                .build();

        pediaRepository.save(pedia);
    }

    public Pedia getPediaByTitle(String title) {
        return pediaRepository.findByTitle(title).orElseThrow(
                () -> new NoSuchElementException("Pedia By Title Not Found"));
    }


    public Pedia getPediaById(Long id) {
        return pediaRepository.findById(id).orElse(null);
    }

    public List<Pedia> findAll() {
        return pediaRepository.findAll();
    }

    // 피디아 서비스
    public Pedia findByTitle(String title) {
        return pediaRepository.findByTitle(title).orElseThrow(
                () -> new NoSuchElementException("Pedia By Title Not Found"));
    }


}


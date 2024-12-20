package org.example.est_team_project2.service.pedia;


import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.pedia.PediaRepository;
import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.dto.pedia.PediaDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PediaService {

    private final PediaRepository pediaRepository;

    public PediaDto save(PediaDto pediaDto) {
        return PediaDto.from(pediaRepository.save(Pedia.from(pediaDto)));
    }

    public Pedia getPediaByTitle(String title) {
        return pediaRepository.findByTitle(title).orElseThrow(
            () -> new NoSuchElementException("Pedia By Title Not Found"));
    }
}

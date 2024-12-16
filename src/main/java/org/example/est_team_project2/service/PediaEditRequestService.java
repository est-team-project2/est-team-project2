package org.example.est_team_project2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.PediaEditRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PediaEditRequestService {

    private final PediaEditRequestRepository pediaEditRequestRepository;

//    public PediaEditRequestDto save(PediaEditRequestDto pediaEditRequestDto) {
//
//    }
}

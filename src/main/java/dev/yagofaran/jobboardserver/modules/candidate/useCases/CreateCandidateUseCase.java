package dev.yagofaran.jobboardserver.modules.candidate.useCases;

import dev.yagofaran.jobboardserver.exceptions.AppException;
import dev.yagofaran.jobboardserver.modules.candidate.entities.CandidateEntity;
import dev.yagofaran.jobboardserver.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new AppException("User already exists");
                });

        return this.candidateRepository.save(candidateEntity);
    }
}

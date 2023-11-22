package dev.yagofaran.jobboardserver.modules.candidate.useCases;

import dev.yagofaran.jobboardserver.exceptions.AppException;
import dev.yagofaran.jobboardserver.modules.candidate.entities.ApplyJobEntity;
import dev.yagofaran.jobboardserver.modules.candidate.repositories.ApplyJobRepository;
import dev.yagofaran.jobboardserver.modules.candidate.repositories.CandidateRepository;
import dev.yagofaran.jobboardserver.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        this.candidateRepository.findById(candidateId)
            .orElseThrow(() -> {
                return new AppException("Candidate not found");
            });

        this.jobRepository.findById(jobId)
            .orElseThrow(() -> {
                return new AppException("Job not found");
            });

        var applyJob = ApplyJobEntity.builder()
            .candidateId(candidateId)
            .jobId(jobId)
            .build();
        return applyJobRepository.save(applyJob);
    }
}

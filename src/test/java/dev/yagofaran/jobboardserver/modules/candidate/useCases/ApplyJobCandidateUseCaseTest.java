package dev.yagofaran.jobboardserver.modules.candidate.useCases;

import dev.yagofaran.jobboardserver.exceptions.AppException;
import dev.yagofaran.jobboardserver.modules.candidate.entities.ApplyJobEntity;
import dev.yagofaran.jobboardserver.modules.candidate.entities.CandidateEntity;
import dev.yagofaran.jobboardserver.modules.candidate.repositories.ApplyJobRepository;
import dev.yagofaran.jobboardserver.modules.candidate.repositories.CandidateRepository;
import dev.yagofaran.jobboardserver.modules.company.entities.JobEntity;
import dev.yagofaran.jobboardserver.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private JobRepository jobRepository;
    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobIfCandidateNotFound() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(AppException.class);
            assertThat(e.getMessage()).isEqualTo("Candidate not found");
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void shouldNotBeAbleToApplyJobIfJobNotFound() {
        var candidateId = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(candidateId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(candidateId, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(AppException.class);
            assertThat(e.getMessage()).isEqualTo("Job not found");
        }
    }

    @Test
    @DisplayName("Should be able to create a new ApplyJob")
    public void shouldBeAbleToCreateNewApplyJob() {
        var candidateId = UUID.randomUUID();
        var jobId = UUID.randomUUID();

        var candidate = new CandidateEntity();
        var job = new JobEntity();

        candidate.setId(candidateId);
        job.setId(jobId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));

        var applyJob = ApplyJobEntity.builder()
            .candidateId(candidateId)
            .jobId(jobId)
            .build();

        var createdApplyJob = ApplyJobEntity.builder()
            .candidateId(candidateId)
            .jobId(jobId)
            .id(UUID.randomUUID())
            .build();

        when(applyJobRepository.save(applyJob)).thenReturn(createdApplyJob);

        var result = applyJobCandidateUseCase.execute(candidateId, jobId);

        assertThat(result).hasFieldOrProperty("id");
        assertThat(result.getId()).isNotNull();
    }
}

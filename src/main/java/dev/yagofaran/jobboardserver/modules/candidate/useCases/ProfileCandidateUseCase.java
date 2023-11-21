package dev.yagofaran.jobboardserver.modules.candidate.useCases;

import dev.yagofaran.jobboardserver.exceptions.AppException;
import dev.yagofaran.jobboardserver.modules.candidate.dto.ProfileCandidateResponseDTO;
import dev.yagofaran.jobboardserver.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID id) {
        var candidate = this.candidateRepository.findById(id)
            .orElseThrow(() -> {
                return new AppException("User not found");
            });

        return ProfileCandidateResponseDTO.builder()
            .username(candidate.getUsername())
            .email(candidate.getEmail())
            .description(candidate.getDescription())
            .name(candidate.getName())
            .id(candidate.getId())
            .build();
    }
}

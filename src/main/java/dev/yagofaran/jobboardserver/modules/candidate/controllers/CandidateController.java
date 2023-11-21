package dev.yagofaran.jobboardserver.modules.candidate.controllers;

import dev.yagofaran.jobboardserver.modules.candidate.dto.ProfileCandidateResponseDTO;
import dev.yagofaran.jobboardserver.modules.candidate.entities.CandidateEntity;
import dev.yagofaran.jobboardserver.modules.candidate.useCases.CreateCandidateUseCase;
import dev.yagofaran.jobboardserver.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("candidates")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;
    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping
    public ResponseEntity<CandidateEntity> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        var result = this.createCandidateUseCase.execute(candidateEntity);

        return ResponseEntity.status(201).body(result);
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ProfileCandidateResponseDTO> profile(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidateId");

        var result = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));

        return ResponseEntity.status(200).body(result);
    }
}

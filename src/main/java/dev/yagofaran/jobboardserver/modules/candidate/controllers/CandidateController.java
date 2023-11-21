package dev.yagofaran.jobboardserver.modules.candidate.controllers;

import dev.yagofaran.jobboardserver.modules.candidate.dto.ProfileCandidateResponseDTO;
import dev.yagofaran.jobboardserver.modules.candidate.entities.CandidateEntity;
import dev.yagofaran.jobboardserver.modules.candidate.useCases.CreateCandidateUseCase;
import dev.yagofaran.jobboardserver.modules.candidate.useCases.ListJobsByFilterUseCase;
import dev.yagofaran.jobboardserver.modules.candidate.useCases.ProfileCandidateUseCase;
import dev.yagofaran.jobboardserver.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("candidates")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;
    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;
    @Autowired
    private ListJobsByFilterUseCase listJobsByFilterUseCase;

    @PostMapping
    public ResponseEntity<CandidateEntity> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        var result = this.createCandidateUseCase.execute(candidateEntity);

        return ResponseEntity.status(201).body(result);
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidate", description = "Candidate info")
    @Operation(
            summary = "Candidate's profile",
            description = "Returns the candidate's profile"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not found")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ProfileCandidateResponseDTO> profile(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidateId");

        var result = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidate", description = "Candidate info")
    @Operation(
        summary = "available job list for candidates",
        description = "Lists all available jobs, based on filter"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<List<JobEntity>> listJobsByFilter(@RequestParam String filter) {
        var result = this.listJobsByFilterUseCase.execute(filter);

        return ResponseEntity.status(200).body(result);
    }
}

package dev.yagofaran.jobboardserver.modules.company.controllers;

import dev.yagofaran.jobboardserver.modules.company.dto.CreateJobDTO;
import dev.yagofaran.jobboardserver.modules.company.entities.JobEntity;
import dev.yagofaran.jobboardserver.modules.company.useCases.CreateJobUseCase;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/companies/jobs")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Jobs", description = "Jobs info")
    @Operation(
            summary = "New job registration",
            description = "Creates a new Job"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = JobEntity.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<JobEntity> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("companyId");

        var jobEntity = JobEntity.builder()
            .companyId(UUID.fromString(companyId.toString()))
            .benefits(createJobDTO.getBenefits())
            .description(createJobDTO.getDescription())
            .level(createJobDTO.getLevel())
            .build();

        var result = this.createJobUseCase.execute(jobEntity);

        return ResponseEntity.status(201).body(result);
    }
}

package dev.yagofaran.jobboardserver.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    private UUID id;

    @Schema(example = "john_wick")
    private String username;

    @Schema(example = "john_wick@continental.com")
    private String email;

    @Schema(example = "Accomplished Java Developer")
    private String description;

    @Schema(example = "John Wick")
    private String name;
}

package dev.yagofaran.jobboardserver.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {
    @Schema(example = "Web Back-End Developer", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "Health Insurance, Gympass", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "Senior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}

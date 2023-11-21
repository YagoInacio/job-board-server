package dev.yagofaran.jobboardserver.modules.candidate.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidates")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(
        example = "John Wick",
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Candidate's name"
    )
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "Field [username] must not contain spaces")
    @Schema(
            example = "john_wick",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Candidate's username"
    )
    private String username;

    @Email(message = "Field [email] must be a valid email")
    @Schema(
            example = "john_wick@continental.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Candidate's email"
    )
    private String email;

    @Length(min = 10, max = 100, message = "Field [password] length must be between 10 and 100")
    @Schema(
        example = "admin@1234",
        minLength = 10,
        maxLength = 100,
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Candidate's password"
    )
    private String password;

    @Schema(
            example = "Accomplished Java Developer",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Candidate's short description"
    )
    private String description;

    private String curriculum;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

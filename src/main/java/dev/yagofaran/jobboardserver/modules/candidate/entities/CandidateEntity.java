package dev.yagofaran.jobboardserver.modules.candidate.entities;

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

    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "Field [username] must not contain spaces")
    private String username;

    @Email(message = "Field [email] must be a valid email")
    private String email;

    @Length(min = 10, max = 100, message = "Field [password] length must be between 10 and 100")
    private String password;

    private String description;

    private String curriculum;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

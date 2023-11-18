package dev.yagofaran.jobboardserver.modules.candidate.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {
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
}

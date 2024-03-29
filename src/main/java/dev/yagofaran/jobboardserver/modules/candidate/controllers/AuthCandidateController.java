package dev.yagofaran.jobboardserver.modules.candidate.controllers;

import dev.yagofaran.jobboardserver.modules.candidate.dto.AuthCandidateRequestDTO;
import dev.yagofaran.jobboardserver.modules.candidate.useCases.AuthCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "Auth", description = "Authentication routes")
public class AuthCandidateController {
    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("candidate")
    @Operation(
        summary = "Authenticate candidate",
        description = "Candidate login"
    )
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var result = this.authCandidateUseCase.execute(authCandidateRequestDTO);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

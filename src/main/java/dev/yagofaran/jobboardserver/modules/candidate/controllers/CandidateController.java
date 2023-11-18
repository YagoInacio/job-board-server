package dev.yagofaran.jobboardserver.modules.candidate.controllers;

import dev.yagofaran.jobboardserver.modules.candidate.entities.CandidateEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("candidates")
public class CandidateController {

    @PostMapping
    public void create(@Valid @RequestBody CandidateEntity candidateEntity) {
        System.out.println("Cadidate: ");
        System.out.println(candidateEntity.getEmail());
    }
}

package dev.yagofaran.jobboardserver.modules.company.controllers;

import dev.yagofaran.jobboardserver.modules.company.entities.JobEntity;
import dev.yagofaran.jobboardserver.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public ResponseEntity<JobEntity> create(@Valid @RequestBody JobEntity jobEntity) {
        var result = this.createJobUseCase.execute(jobEntity);

        return ResponseEntity.status(201).body(result);
    }
}

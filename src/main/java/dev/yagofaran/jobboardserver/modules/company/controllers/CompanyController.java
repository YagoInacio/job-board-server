package dev.yagofaran.jobboardserver.modules.company.controllers;

import dev.yagofaran.jobboardserver.modules.company.entities.CompanyEntity;
import dev.yagofaran.jobboardserver.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("companies")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping
    public ResponseEntity<CompanyEntity> create(@Valid @RequestBody CompanyEntity companyEntity) {
        var result =  this.createCompanyUseCase.execute(companyEntity);

        return ResponseEntity.status(201).body(result);
    }
}

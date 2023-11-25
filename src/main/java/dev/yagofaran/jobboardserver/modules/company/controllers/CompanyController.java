package dev.yagofaran.jobboardserver.modules.company.controllers;

import dev.yagofaran.jobboardserver.modules.company.entities.CompanyEntity;
import dev.yagofaran.jobboardserver.modules.company.useCases.CreateCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("companies")
@Tag(name = "Company", description = "Company info")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping
    @Operation(
            summary = "Company creation",
            description = "Creates a new company"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = CompanyEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "User already exists",
                    content = @Content(schema = @Schema(implementation = Void.class))
            )
    })
    public ResponseEntity<CompanyEntity> create(@Valid @RequestBody CompanyEntity companyEntity) {
        var result =  this.createCompanyUseCase.execute(companyEntity);

        return ResponseEntity.status(201).body(result);
    }
}

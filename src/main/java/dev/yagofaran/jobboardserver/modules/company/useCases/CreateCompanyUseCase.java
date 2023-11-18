package dev.yagofaran.jobboardserver.modules.company.useCases;

import dev.yagofaran.jobboardserver.exceptions.AppException;
import dev.yagofaran.jobboardserver.modules.company.entities.CompanyEntity;
import dev.yagofaran.jobboardserver.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
            .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
            .ifPresent((company) -> {
                throw new AppException("User already exists");
            });

        return this.companyRepository.save(companyEntity);
    }
}

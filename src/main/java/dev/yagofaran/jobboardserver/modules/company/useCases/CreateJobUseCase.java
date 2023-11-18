package dev.yagofaran.jobboardserver.modules.company.useCases;

import dev.yagofaran.jobboardserver.exceptions.AppException;
import dev.yagofaran.jobboardserver.modules.company.entities.JobEntity;
import dev.yagofaran.jobboardserver.modules.company.repositories.CompanyRepository;
import dev.yagofaran.jobboardserver.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        var companyExists = this.companyRepository
                .findById(jobEntity.getCompanyId());

        if (companyExists.isEmpty()) {
            throw new AppException("Company not found");
        }

        return this.jobRepository.save(jobEntity);
    }
}

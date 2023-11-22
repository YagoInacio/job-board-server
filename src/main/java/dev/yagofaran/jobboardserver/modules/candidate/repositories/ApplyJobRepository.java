package dev.yagofaran.jobboardserver.modules.candidate.repositories;

import dev.yagofaran.jobboardserver.modules.candidate.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}

package dev.yagofaran.jobboardserver.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public record AuthCompanyRequestDTO(String username, String password) {}

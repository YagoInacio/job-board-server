package dev.yagofaran.jobboardserver.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.yagofaran.jobboardserver.exceptions.AppException;
import dev.yagofaran.jobboardserver.modules.company.dto.AuthCompanyRequestDTO;
import dev.yagofaran.jobboardserver.modules.company.dto.AuthCompanyResponseDTO;
import dev.yagofaran.jobboardserver.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthCompanyResponseDTO execute(AuthCompanyRequestDTO authCompanyRequestDTO) throws AuthenticationException {
        var company = this.companyRepository
            .findByUsername(authCompanyRequestDTO.username())
            .orElseThrow(() -> {
                return new AppException("username/password incorrect");
            });

        var passwordMatches = this.passwordEncoder
            .matches(authCompanyRequestDTO.password(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token =  JWT.create().withIssuer("yagofaran")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        return AuthCompanyResponseDTO.builder()
                .accessToken(token)
                .expiresIn(expiresIn.toEpochMilli())
                .build();
    }
}

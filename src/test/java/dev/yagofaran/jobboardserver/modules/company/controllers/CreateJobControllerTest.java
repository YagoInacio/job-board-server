package dev.yagofaran.jobboardserver.modules.company.controllers;

import dev.yagofaran.jobboardserver.modules.company.dto.CreateJobDTO;
import dev.yagofaran.jobboardserver.modules.company.entities.CompanyEntity;
import dev.yagofaran.jobboardserver.modules.company.repositories.CompanyRepository;
import dev.yagofaran.jobboardserver.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {
    @Value("${security.token.secret}")
    private String secretKey;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }

    @Test
    public void shouldBeAbleToCreateNewJob() throws Exception {
        var company = CompanyEntity.builder()
            .description("DESCRIPTION TEST")
            .email("EMAIL@TEST.COM")
            .username("USERNAME_TEST")
            .name("NAME TEST")
            .password("PASSWORD_TEST")
            .build();

        this.companyRepository.saveAndFlush(company);

        var content = CreateJobDTO.builder()
            .description("DESCRIPTION TEST")
            .benefits("BENEFITS TEST")
            .level("LEVEL TEST")
            .build();

        var result = mvc.perform(MockMvcRequestBuilders
            .post("/companies/jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(content))
            .header("Authorization", TestUtils.generateToken(company.getId(), secretKey))
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        System.out.println(result);
    }

    @Test
    public void shouldNotBeAbleToCreateNewJobIfCompanyNotFound() throws Exception {
        var content = CreateJobDTO.builder()
                .description("DESCRIPTION TEST")
                .benefits("BENEFITS TEST")
                .level("LEVEL TEST")
                .build();

        mvc.perform(MockMvcRequestBuilders
            .post("/companies/jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(content))
            .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), secretKey))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Company not found"));
    }
}

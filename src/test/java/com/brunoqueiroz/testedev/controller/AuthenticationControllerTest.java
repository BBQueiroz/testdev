package com.brunoqueiroz.testedev.controller;

import com.brunoqueiroz.testedev.domain.user.UserRole;
import com.brunoqueiroz.testedev.domain.user.Users;
import com.brunoqueiroz.testedev.domain.user.UsersRepository;
import com.brunoqueiroz.testedev.infra.security.dtos.AuthenticationDTO;
import com.brunoqueiroz.testedev.infra.security.dtos.LoginResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<LoginResponseDTO> loginResponseDTOJacksonTester;

    @Autowired
    private JacksonTester<AuthenticationDTO> authenticationDTOJacksonTester;

    @Autowired
    private UsersRepository usersRepository;

    @BeforeAll
    @Transactional
    void beforeAll() {
        Users user = new Users("teste", new BCryptPasswordEncoder().encode("1234"), UserRole.ADMIN);
        usersRepository.save(user);
    }

    @AfterAll
    void afterAll(){
        usersRepository.deleteAll();
    }

    @Test
    void login() throws Exception {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO("teste", "1234");
        String credentials = authenticationDTOJacksonTester.write(authenticationDTO).getJson();
        String endpoint = "/auth/login";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(endpoint).contentType(APPLICATION_JSON).content(credentials);

        // When
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    void register() {
    }
}
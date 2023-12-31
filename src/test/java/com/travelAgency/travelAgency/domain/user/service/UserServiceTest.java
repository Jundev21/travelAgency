package com.travelAgency.travelAgency.domain.user.service;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.travelAgency.travelAgency.domain.user.dto.request.LoginRequestDTO;
import com.travelAgency.travelAgency.domain.user.dto.request.RegisterRequestDTO;
import com.travelAgency.travelAgency.domain.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserServiceTest {


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;
	@MockBean
	private UserService memberService;

	@InjectMocks
	private UserRepository memberRepository;

	private ObjectMapper objectMapper = new ObjectMapper();


	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
			.webAppContextSetup(this.context)
			.apply(springSecurity())
			.build();
	}

	@Test
	@DisplayName("회원가입")
	public void memberRegister() throws Exception {

		RegisterRequestDTO registerRequestDTO = RegisterRequestDTO.builder()
			.email("test@gmail.com")
			.age(23)
			.name("test owner")
			.password("test123123123")
			.build();

		String content = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.writeValueAsString(registerRequestDTO);

		mockMvc.perform(post("/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
			)
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@DisplayName("로그인 성공")
	public void memberLogin() throws Exception {

		memberRegister();

		LoginRequestDTO loginRequestDto = LoginRequestDTO.builder()
			.email("test@gmail.com")
			.password("test")
			.build();

		String content2 = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.writeValueAsString(loginRequestDto);

		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content2)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.token.accessToken").isString())
			.andExpect(jsonPath("$.data.token.accessTokenExpiresIn").isNumber())
			.andExpect(jsonPath("$.data.token.refreshToken").isString())
			.andDo(print());
	}

	@Test
	@DisplayName("로그인 실패")
	public void memberLoginFail() throws Exception {

		memberRegister();

		LoginRequestDTO loginRequestDto = LoginRequestDTO.builder()
			.email("test@gmail.com")
			.password("test")
			.build();

		String content2 = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.writeValueAsString(loginRequestDto);

		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content2)
			).andExpect(status().isOk())
			.andDo(print());
		;

	}

}
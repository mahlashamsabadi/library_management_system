package com.library_managment.user;

import com.library_managment.user.model.entity.ApplicationUser;
import com.library_managment.user.model.entity.Role;
import com.library_managment.user.service.api.JWTService;
import com.library_managment.user.service.imp.JWTServiceImpl;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserApplicationTests {

	@InjectMocks
	private JWTService jwtService = new JWTServiceImpl(); // Your class containing generateToken method

	@Mock
	private SecretKey generateSigningKey; // Mock the key generation method

	private ApplicationUser testUser;
	private static final String ROLE_CLAIM = "roles";
	private static final String ID_CLAIM = "id";
	private static final long ACCESS_TOKEN_EXPIRATION_DURATION = 86400000; // 24 hours

	@BeforeEach
	void setUp() {
		testUser = new ApplicationUser();
		testUser.setId(123L);
		testUser.setRoles(Arrays.asList(
				Role.builder()
						.name("ROLE_ADMIN")
						.description("test1").build() ,
				Role.builder()
						.name("ROLE_USER")
						.description("test2").build()));

		// Mock the signing key
		when(generateSigningKey.getAlgorithm()).thenReturn("HmacSHA256");
		when(generateSigningKey.getEncoded()).thenReturn("test-secret-key".getBytes());
	}

	@Test
	void generateToken_ShouldReturnValidJwtToken() {
		// Arrange
//		SecretKey realKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		when(jwtService.generateSigningKey()).thenReturn(realKey);

		// Act
		String token = jwtService.generateToken(testUser);

		// Assert
		assertNotNull(token);
		assertTrue(token.split("\\.").length == 3); // JWT has 3 parts

		System.out.println("token is : " + token);
	}


}

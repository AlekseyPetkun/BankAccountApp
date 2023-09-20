package com.github.alekseypetkun.bankaccountapp.security;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PBFDK2PasswordEncoderTest {

    @Autowired
    private PBFDK2PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {
        String rawPassword = "mypassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    public void testMatches() {
        String rawPassword = "mypassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }

}
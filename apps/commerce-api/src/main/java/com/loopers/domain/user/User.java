package com.loopers.domain.user;

import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Column(name = "login_id", nullable = false, updatable = false)
    private String loginId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birth_date")
    private String birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private String gender;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern LOGIN_ID_PATTERN = Pattern.compile("^[A-Za-z0-9]{1,10}$");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);

    public User(String username, String loginId, String password, String email, String birthDate, String gender) {
        validateUserName(username);
        validateLoginId(loginId);
        validatePassword(password);
        validateEmail(email);
        validateBirthDate(birthDate);
        validateGender(gender);

        this.username = username;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    private void validateUserName(String username) {
        if (!StringUtils.hasText(username)) {
            throw new CoreException(ErrorType.BAD_REQUEST, "Username cannot be empty");
        }
    }

    private void validateLoginId(String loginId) {
        if (!StringUtils.hasText(loginId) || !LOGIN_ID_PATTERN.matcher(loginId).matches()) {
            throw new CoreException(ErrorType.BAD_REQUEST, "Invalid login ID format");
        }
    }

    private void validatePassword(String password) {
        if (!StringUtils.hasText(password)) {
            throw new CoreException(ErrorType.BAD_REQUEST, "Password cannot be empty");
        }
    }

    private void validateEmail(String email) {
        if (!StringUtils.hasText(email) || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new CoreException(ErrorType.BAD_REQUEST, "Invalid email format");
        }
    }

    private void validateBirthDate(String birthDate) {
        if (!StringUtils.hasText(birthDate)) {
            throw new CoreException(ErrorType.BAD_REQUEST, "Birth date cannot be empty");
        }

        try {
            LocalDate.parse(birthDate, DATE_TIME_FORMATTER);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid birth date format. Expected uuuu-MM-dd");
        }
    }

    private void validateGender(String gender) {
        if (!StringUtils.hasText(gender)) {
            throw new CoreException(ErrorType.BAD_REQUEST, "Gender cannot be empty");
        }
    }
}

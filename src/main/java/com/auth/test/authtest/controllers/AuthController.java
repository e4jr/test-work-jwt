package com.auth.test.authtest.controllers;


import com.auth.test.authtest.exceptions.LoginException;
import com.auth.test.authtest.exceptions.RegistrationException;
import com.auth.test.authtest.models.ErrorResponse;
import com.auth.test.authtest.models.TokenResponse;
import com.auth.test.authtest.models.User;
import com.auth.test.authtest.service.UserReg;
import com.auth.test.authtest.service.TokenGenerate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
     UserReg userService;
    @Autowired
    TokenGenerate tokenService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.register(user.getGuid(), user.getUserSecret());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/token")
    public TokenResponse getAccessToken(@RequestBody User user) {
        userService.checkCredentials(user.getGuid(), user.getUserSecret());
        return new TokenResponse(tokenService.generateAccessToken(user.getGuid()));
    }

    @PostMapping("/refresh")
    public TokenResponse getRefreshToken(@RequestBody User user) {
        userService.checkCredentials(user.getGuid(), user.getUserSecret());
        tokenService.sendEmail();
        return new TokenResponse(tokenService.generateRefreshToken(user.getGuid()));
    }



    @ExceptionHandler({RegistrationException.class, LoginException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}

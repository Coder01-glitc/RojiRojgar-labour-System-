package com.RojiRojgar.controller;

import com.RojiRojgar.config.JwtUtil;
import com.RojiRojgar.model.User;
import com.RojiRojgar.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class AuthController {

    // =========================
    // STORE OTP
    // =========================

    private String generatedOtp = "";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody User user
    ) {

        Optional<User> existingUser =

                userRepository.findByEmail(
                        user.getEmail()
                );

        if(existingUser.isPresent()) {

            return ResponseEntity
                    .badRequest()
                    .body("Email Already Registered");
        }

        user.setPassword(

                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        userRepository.save(user);

        return ResponseEntity.ok(
                "User Registered Successfully"
        );
    }

    // =========================
    // SEND OTP
    // =========================

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp() {

        int otp =

                (int)(Math.random() * 9000)

                        + 1000;

        generatedOtp =

                String.valueOf(otp);

        System.out.println(
                "Generated OTP: " + generatedOtp
        );

        return ResponseEntity.ok(
                "OTP Sent Successfully"
        );
    }

    // =========================
    // VERIFY OTP
    // =========================

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(

            @RequestBody Map<String,String> data
    ) {

        String enteredOtp =

                data.get("otp");

        if(generatedOtp.equals(enteredOtp)) {

            return ResponseEntity.ok(
                    "OTP Verified"
            );
        }

        return ResponseEntity
                .badRequest()
                .body("Invalid OTP");
    }

    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public ResponseEntity<?> login(

            @RequestBody Map<String,String> data
    ) {

        String email =

                data.get("email");

        String password =

                data.get("password");

        // Find user

        Optional<User> optionalUser =

                userRepository.findByEmail(email);

        // User not found

        if(optionalUser.isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("User Not Found");
        }

        User user =

                optionalUser.get();

        // Password check

        boolean match =

                passwordEncoder.matches(
                        password,
                        user.getPassword()
                );

        if(!match) {

            return ResponseEntity
                    .badRequest()
                    .body("Invalid Password");
        }

        // Generate JWT Token

        String token =

                JwtUtil.generateToken(
                        user.getEmail(),
                        user.getRole()
                );

        // Response JSON

        Map<String,String> response =

                new HashMap<>();

        response.put("token", token);

        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }
}
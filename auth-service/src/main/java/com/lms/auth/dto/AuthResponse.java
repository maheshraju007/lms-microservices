package com.lms.auth.dto;

public class AuthResponse {
    private String token;
    private String email;
    private String role;
    private String firstName;
    private String lastName;

    public AuthResponse(String token, String email, String role, String firstName, String lastName) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getToken() { return token; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}

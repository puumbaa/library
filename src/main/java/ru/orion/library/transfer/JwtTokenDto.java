package ru.orion.library.transfer;

import lombok.Data;

@Data
public class JwtTokenDto {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String login;
    private String role;

    public JwtTokenDto(String jwt, Long id, String login, String role) {
        token = jwt;
        this.id = id;
        this.login = login;
        this.role = role;
    }
}

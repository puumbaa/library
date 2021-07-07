package ru.orion.library.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.orion.library.enums.AccountRole;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenDto {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String login;
    private AccountRole role;

}

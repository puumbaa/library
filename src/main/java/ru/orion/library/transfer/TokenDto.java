package ru.orion.library.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.orion.library.models.Token;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String token;

    public static TokenDto from(Token token){
        return new TokenDto(token.getValue());
    }
}

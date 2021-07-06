package ru.orion.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.orion.library.models.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByValue(String value);
}

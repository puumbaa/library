package ru.orion.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.orion.library.enums.BookStatus;
import ru.orion.library.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByStatus(BookStatus bookStatus);

    @Override
    Optional<Book> findById(Long bookId);
}

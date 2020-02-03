package com.example.demotestcb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    private final NotificationService notificationService;

    public Book createBook(Book book) {
        book.publish();
        Book newBook = repository.save(book);
        notificationService.publishEvent(newBook);
        return newBook;
    }
}

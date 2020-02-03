package com.example.demotestcb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock BookRepository bookRepository;
    @Mock NotificationService notificationService;

    @Test
    @DisplayName("새 책 만들기")
    void createNewBook() {
        // Given
        Book book = Book.builder().title("새 책").author("백기선").introduction("테스트가 어쩌구 저쩌구").build();
        Book newBook = Book.builder().title("새 책").author("백기선").introduction("테스트가 어쩌구 저쩌구").build();
        newBook.publish();
        BookService bookService = new BookService(bookRepository, notificationService);
        given(bookRepository.save(book)).willReturn(newBook);

        // When
        Book bookToCheck = bookService.createBook(book);

        // Then
        assertAll(
                () -> assertNotNull(bookToCheck.getPublishedDate()),
                () -> assertEquals(LocalDate.now(), bookToCheck.getPublishedDate()),
                () -> assertTrue(bookToCheck.isPublished())
        );
        then(notificationService).should().publishEvent(newBook);
    }

}
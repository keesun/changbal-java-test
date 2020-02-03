package com.example.demotestcb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    @DisplayName("새 책 만들기")
    public void create_New_Book() {
        Book book = Book.builder()
                .title("새 책입니다.")
                .isbn("123j1l2k3j1l23")
                .author("백기선")
                .build();

        assertAll(
                () -> assertEquals("백기선", book.getAuthor()),
                () -> assertFalse(book.isPublished()),
                () -> assertNull(book.getPublishedDate())
        );

    }

}
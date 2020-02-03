package com.example.demotestcb;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDate;


@Entity
@Getter @Setter @EqualsAndHashCode
@Builder @AllArgsConstructor @NoArgsConstructor
public class Book {

    @Id @GeneratedValue
    private Long id;

    private String isbn;

    private String title;

    private LocalDate publishedDate;

    private String author;

    @Lob
    private String introduction;

    private boolean published;

    public void publish() {
        this.publishedDate = LocalDate.now();
        this.published = true;
    }
}

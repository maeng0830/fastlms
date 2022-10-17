package com.maeng0830.fastlms.course.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long categoryId;

    String imagePath;
    String keyWord;
    String subject;

    @Column(length = 1000)
    String summary;

    @Lob
    String contents;

    long price;
    long salePrice;
    LocalDate saleEndDt;

    LocalDateTime regDt;
    LocalDateTime udtDt;

    String fileName;
    String urlFileName;
}

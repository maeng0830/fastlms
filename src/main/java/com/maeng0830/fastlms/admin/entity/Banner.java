package com.maeng0830.fastlms.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String bannerName; // 배너명
    LocalDateTime regDt; // 등록일
    String linkUrl; // 링크 주소(클릭 했을 때 이동 경로)
    String openMethod; // 오픈 방법(새창, 현재창)
    int sortValue; // 정렬 순서
    boolean postYn; // 공개(게시) 여부

    String fileName;
    String urlFileName;
}

package com.maeng0830.fastlms.admin.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerInput {
    long id;

    String bannerName; // 배너명
    LocalDateTime regDt; // 등록일
    String linkUrl; // 링크 주소(클릭 했을 때 이동 경로)
    String openMethod; // 오픈 방법(새창, 현재창)
    int sortValue; // 정렬 순서
    boolean postYn; // 공개(게시) 여부

    String fileName;
    String urlFileName;

    String idList;
}

package com.maeng0830.fastlms.admin.dto;

import com.maeng0830.fastlms.admin.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BannerDto {
    Long id;

    String bannerName; // 배너명
    LocalDateTime regDt; // 등록일
    String linkUrl; // 링크 주소(클릭 했을 때 이동 경로)
    String openMethod; // 오픈 방법(새창, 현재창)
    int sortValue; // 정렬 순서
    boolean postYn; // 공개(게시) 여부

    String fileName;
    String urlFileName;

    //추가 컬럼
    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .regDt(banner.getRegDt())
                .linkUrl(banner.getLinkUrl())
                .openMethod(banner.getOpenMethod())
                .sortValue(banner.getSortValue())
                .postYn(banner.isPostYn())
                .fileName(banner.getFileName())
                .urlFileName(banner.getUrlFileName())
                .build();
    }

    public static List<BannerDto> of(List<Banner> banners) {
        if (banners == null) {
            return null;
        }

        List<BannerDto> bannerDtoList = new ArrayList<>();
        for (Banner x: banners) {
            bannerDtoList.add(BannerDto.of(x));
        }

        return bannerDtoList;
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    }
}

package com.maeng0830.fastlms.admin.service;

import com.maeng0830.fastlms.admin.dto.BannerDto;
import com.maeng0830.fastlms.admin.model.BannerInput;
import com.maeng0830.fastlms.admin.model.BannerParam;

import java.util.List;

public interface BannerService {
    List<BannerDto> list(BannerParam parameter);

    //배너 상세 정보
    BannerDto getById(long id);

    // 배너 등록
    boolean add(BannerInput parameter);

    // 배너 정보 수정
    boolean set(BannerInput parameter);

    boolean del(String idList);

    List<BannerDto> bannerSlider();
}

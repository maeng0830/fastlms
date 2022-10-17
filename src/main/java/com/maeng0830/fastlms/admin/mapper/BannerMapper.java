package com.maeng0830.fastlms.admin.mapper;

import com.maeng0830.fastlms.admin.dto.BannerDto;
import com.maeng0830.fastlms.admin.model.BannerParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    long selectListCount(BannerParam parameter);
    List<BannerDto> selectList(BannerParam parameter);

    List<BannerDto> selectBannerSlider();
}

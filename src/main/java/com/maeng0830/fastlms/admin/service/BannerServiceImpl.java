package com.maeng0830.fastlms.admin.service;

import com.maeng0830.fastlms.admin.dto.BannerDto;
import com.maeng0830.fastlms.admin.entity.Banner;
import com.maeng0830.fastlms.admin.mapper.BannerMapper;
import com.maeng0830.fastlms.admin.model.BannerInput;
import com.maeng0830.fastlms.admin.model.BannerParam;
import com.maeng0830.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    // 배너 목록 불러오기
    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> bannerDtoList = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(bannerDtoList)) {
            int i = 0;
            for (BannerDto x: bannerDtoList) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return bannerDtoList;
    }

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    // 배너 등록
    @Override
    public boolean add(BannerInput parameter) {

        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .regDt(parameter.getRegDt())
                .linkUrl(parameter.getLinkUrl())
                .openMethod(parameter.getOpenMethod())
                .sortValue(parameter.getSortValue())
                .postYn(parameter.isPostYn())
                .fileName(parameter.getFileName())
                .urlFileName(parameter.getUrlFileName())
                .regDt(LocalDateTime.now())
                .build();

        bannerRepository.save(banner);

        return true;
    }

    // 배너 수정
    @Override
    public boolean set(BannerInput parameter) {

        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if (!optionalBanner.isPresent()) {
            return false;
        }

        Banner banner = optionalBanner.get();
        banner.setBannerName(parameter.getBannerName());
        banner.setLinkUrl(parameter.getLinkUrl());
        banner.setOpenMethod(parameter.getOpenMethod());
        banner.setSortValue(parameter.getSortValue());
        banner.setPostYn(parameter.isPostYn());
        banner.setFileName(parameter.getFileName());
        banner.setUrlFileName(parameter.getUrlFileName());
        bannerRepository.save(banner);

        return true;
    }

    // 배너 삭제
    @Override
    public boolean del(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x: ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    // 메인 페이지 배너 슬라이더
    @Override
    public List<BannerDto> bannerSlider() {

        List<BannerDto> bannerDtoList = bannerMapper.selectBannerSlider();
        return bannerDtoList;
    }
}

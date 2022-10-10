package com.maeng0830.fastlms.admin.mapper;

import com.maeng0830.fastlms.admin.dto.CategoryDto;
import com.maeng0830.fastlms.admin.dto.MemberDto;
import com.maeng0830.fastlms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDto> select(CategoryDto parameter);
}

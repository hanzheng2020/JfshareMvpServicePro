package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbSystemInformation;
import com.jfshare.mvp.server.model.TbSystemInformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSystemInformationMapper {
    long countByExample(TbSystemInformationExample example);

    int deleteByExample(TbSystemInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSystemInformation record);

    int insertSelective(TbSystemInformation record);

    List<TbSystemInformation> selectByExample(TbSystemInformationExample example);

    TbSystemInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSystemInformation record, @Param("example") TbSystemInformationExample example);

    int updateByExample(@Param("record") TbSystemInformation record, @Param("example") TbSystemInformationExample example);

    int updateByPrimaryKeySelective(TbSystemInformation record);

    int updateByPrimaryKey(TbSystemInformation record);
}
package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbLevelInfo;
import com.jfshare.mvp.server.model.TbLevelInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbLevelInfoMapper {
    long countByExample(TbLevelInfoExample example);

    int deleteByExample(TbLevelInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbLevelInfo record);

    int insertSelective(TbLevelInfo record);

    List<TbLevelInfo> selectByExample(TbLevelInfoExample example);

    TbLevelInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbLevelInfo record, @Param("example") TbLevelInfoExample example);

    int updateByExample(@Param("record") TbLevelInfo record, @Param("example") TbLevelInfoExample example);

    int updateByPrimaryKeySelective(TbLevelInfo record);

    int updateByPrimaryKey(TbLevelInfo record);
}
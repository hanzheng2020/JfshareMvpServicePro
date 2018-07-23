package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbJfRaiders;
import com.jfshare.mvp.server.model.TbJfRaidersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbJfRaidersMapper {
    int countByExample(TbJfRaidersExample example);

    int deleteByExample(TbJfRaidersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbJfRaiders record);

    int insertSelective(TbJfRaiders record);

    List<TbJfRaiders> selectByExampleWithBLOBs(TbJfRaidersExample example);

    List<TbJfRaiders> selectByExample(TbJfRaidersExample example);

    TbJfRaiders selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbJfRaiders record, @Param("example") TbJfRaidersExample example);

    int updateByExampleWithBLOBs(@Param("record") TbJfRaiders record, @Param("example") TbJfRaidersExample example);

    int updateByExample(@Param("record") TbJfRaiders record, @Param("example") TbJfRaidersExample example);

    int updateByPrimaryKeySelective(TbJfRaiders record);

    int updateByPrimaryKeyWithBLOBs(TbJfRaiders record);

    int updateByPrimaryKey(TbJfRaiders record);
}
package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbAppVerinfo;
import com.jfshare.mvp.server.model.TbAppVerinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAppVerinfoMapper {
    int countByExample(TbAppVerinfoExample example);

    int deleteByExample(TbAppVerinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAppVerinfo record);

    int insertSelective(TbAppVerinfo record);

    List<TbAppVerinfo> selectByExample(TbAppVerinfoExample example);

    TbAppVerinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAppVerinfo record, @Param("example") TbAppVerinfoExample example);

    int updateByExample(@Param("record") TbAppVerinfo record, @Param("example") TbAppVerinfoExample example);

    int updateByPrimaryKeySelective(TbAppVerinfo record);

    int updateByPrimaryKey(TbAppVerinfo record);
}
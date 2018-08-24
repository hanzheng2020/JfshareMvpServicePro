package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbAppVerifySetting;
import com.jfshare.mvp.server.model.TbAppVerifySettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAppVerifySettingMapper {
    long countByExample(TbAppVerifySettingExample example);

    int deleteByExample(TbAppVerifySettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAppVerifySetting record);

    int insertSelective(TbAppVerifySetting record);

    List<TbAppVerifySetting> selectByExample(TbAppVerifySettingExample example);

    TbAppVerifySetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAppVerifySetting record, @Param("example") TbAppVerifySettingExample example);

    int updateByExample(@Param("record") TbAppVerifySetting record, @Param("example") TbAppVerifySettingExample example);

    int updateByPrimaryKeySelective(TbAppVerifySetting record);

    int updateByPrimaryKey(TbAppVerifySetting record);
}
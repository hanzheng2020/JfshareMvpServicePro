package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbProductUrl;
import com.jfshare.mvp.server.model.TbProductUrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProductUrlMapper {
    long countByExample(TbProductUrlExample example);

    int deleteByExample(TbProductUrlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProductUrl record);

    int insertSelective(TbProductUrl record);

    List<TbProductUrl> selectByExample(TbProductUrlExample example);

    TbProductUrl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProductUrl record, @Param("example") TbProductUrlExample example);

    int updateByExample(@Param("record") TbProductUrl record, @Param("example") TbProductUrlExample example);

    int updateByPrimaryKeySelective(TbProductUrl record);

    int updateByPrimaryKey(TbProductUrl record);
}
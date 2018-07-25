package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbProductItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProductItemMapper {
    long countByExample(TbProductItemExample example);

    int deleteByExample(TbProductItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProductItem record);

    int insertSelective(TbProductItem record);

    List<TbProductItem> selectByExample(TbProductItemExample example);

    TbProductItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProductItem record, @Param("example") TbProductItemExample example);

    int updateByExample(@Param("record") TbProductItem record, @Param("example") TbProductItemExample example);

    int updateByPrimaryKeySelective(TbProductItem record);

    int updateByPrimaryKey(TbProductItem record);
}
package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbProductDetail;
import com.jfshare.mvp.server.model.TbProductDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProductDetailMapper {
    int countByExample(TbProductDetailExample example);

    int deleteByExample(TbProductDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProductDetail record);

    int insertSelective(TbProductDetail record);

    List<TbProductDetail> selectByExample(TbProductDetailExample example);

    TbProductDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProductDetail record, @Param("example") TbProductDetailExample example);

    int updateByExample(@Param("record") TbProductDetail record, @Param("example") TbProductDetailExample example);

    int updateByPrimaryKeySelective(TbProductDetail record);

    int updateByPrimaryKey(TbProductDetail record);
}
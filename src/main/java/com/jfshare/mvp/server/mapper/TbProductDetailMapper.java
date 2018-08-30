package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbProductDetail;
import com.jfshare.mvp.server.model.TbProductDetailExample;
import com.jfshare.mvp.server.model.TbProductDetailWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProductDetailMapper {
    long countByExample(TbProductDetailExample example);

    int deleteByExample(TbProductDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProductDetailWithBLOBs record);

    int insertSelective(TbProductDetailWithBLOBs record);

    List<TbProductDetailWithBLOBs> selectByExampleWithBLOBs(TbProductDetailExample example);

    List<TbProductDetail> selectByExample(TbProductDetailExample example);

    TbProductDetailWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProductDetailWithBLOBs record, @Param("example") TbProductDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") TbProductDetailWithBLOBs record, @Param("example") TbProductDetailExample example);

    int updateByExample(@Param("record") TbProductDetail record, @Param("example") TbProductDetailExample example);

    int updateByPrimaryKeySelective(TbProductDetailWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbProductDetailWithBLOBs record);

    int updateByPrimaryKey(TbProductDetail record);
}
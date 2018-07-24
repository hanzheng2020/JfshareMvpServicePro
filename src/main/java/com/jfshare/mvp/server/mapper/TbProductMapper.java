package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductExample;
import com.jfshare.mvp.server.model.TbProductWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProductMapper {
    int countByExample(TbProductExample example);

    int deleteByExample(TbProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProductWithBLOBs record);

    int insertSelective(TbProductWithBLOBs record);

    List<TbProductWithBLOBs> selectByExampleWithBLOBs(TbProductExample example);

    List<TbProduct> selectByExample(TbProductExample example);

    TbProductWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProductWithBLOBs record, @Param("example") TbProductExample example);

    int updateByExampleWithBLOBs(@Param("record") TbProductWithBLOBs record, @Param("example") TbProductExample example);

    int updateByExample(@Param("record") TbProduct record, @Param("example") TbProductExample example);

    int updateByPrimaryKeySelective(TbProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbProductWithBLOBs record);

    int updateByPrimaryKey(TbProduct record);
}
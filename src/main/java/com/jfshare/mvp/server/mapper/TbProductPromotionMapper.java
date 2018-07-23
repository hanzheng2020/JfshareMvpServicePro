package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.mapper.TbProductPromotion;
import com.jfshare.mvp.server.mapper.TbProductPromotionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProductPromotionMapper {
    long countByExample(TbProductPromotionExample example);

    int deleteByExample(TbProductPromotionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProductPromotion record);

    int insertSelective(TbProductPromotion record);

    List<TbProductPromotion> selectByExample(TbProductPromotionExample example);

    TbProductPromotion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProductPromotion record, @Param("example") TbProductPromotionExample example);

    int updateByExample(@Param("record") TbProductPromotion record, @Param("example") TbProductPromotionExample example);

    int updateByPrimaryKeySelective(TbProductPromotion record);

    int updateByPrimaryKey(TbProductPromotion record);
}
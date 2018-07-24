package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.model.TbProductItemShowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProductItemShowMapper {
    int countByExample(TbProductItemShowExample example);

    int deleteByExample(TbProductItemShowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProductItemShow record);

    int insertSelective(TbProductItemShow record);

    List<TbProductItemShow> selectByExample(TbProductItemShowExample example);

    TbProductItemShow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProductItemShow record, @Param("example") TbProductItemShowExample example);

    int updateByExample(@Param("record") TbProductItemShow record, @Param("example") TbProductItemShowExample example);

    int updateByPrimaryKeySelective(TbProductItemShow record);

    int updateByPrimaryKey(TbProductItemShow record);
}
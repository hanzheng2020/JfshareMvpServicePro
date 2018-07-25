package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbJvjindouRule;
import com.jfshare.mvp.server.model.TbJvjindouRuleExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbJvjindouRuleMapper {
    int countByExample(TbJvjindouRuleExample example);

    int deleteByExample(TbJvjindouRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbJvjindouRule record);

    int insertSelective(TbJvjindouRule record);

    List<TbJvjindouRule> selectByExample(TbJvjindouRuleExample example);

    TbJvjindouRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbJvjindouRule record, @Param("example") TbJvjindouRuleExample example);

    int updateByExample(@Param("record") TbJvjindouRule record, @Param("example") TbJvjindouRuleExample example);

    int updateByPrimaryKeySelective(TbJvjindouRule record);

    int updateByPrimaryKey(TbJvjindouRule record);
}
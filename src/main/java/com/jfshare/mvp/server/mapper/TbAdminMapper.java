package com.jfshare.mvp.server.mapper;

import com.jfshare.mvp.server.model.TbAdmin;
import com.jfshare.mvp.server.model.TbAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAdminMapper {
    int countByExample(TbAdminExample example);

    int deleteByExample(TbAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAdmin record);

    int insertSelective(TbAdmin record);

    List<TbAdmin> selectByExample(TbAdminExample example);

    TbAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAdmin record, @Param("example") TbAdminExample example);

    int updateByExample(@Param("record") TbAdmin record, @Param("example") TbAdminExample example);

    int updateByPrimaryKeySelective(TbAdmin record);

    int updateByPrimaryKey(TbAdmin record);
}
package com.jfshare.mvp.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

/**
 * 一个通用的执行sql的dao
 * @author fengxiang
 * @date 2018-07-25
 */
@Repository
public class CommonDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 执行sql方法
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> executeSql(final String sql) {
		return jdbcTemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				return ps;
			}
		}, new PreparedStatementCallback<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.execute();
		        ResultSet rs = ps.getResultSet();
		        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		        while (rs != null && rs.next()) {
		        	Map<String, Object> map = new HashMap<>();
		        	int columnCount = rs.getMetaData().getColumnCount();
		        	for (int i = 1; i <= columnCount; i ++) {
		        		map.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
		        	}
		        	resultList.add(map);
		        }
		        return resultList;
			}
		});      
	}
}

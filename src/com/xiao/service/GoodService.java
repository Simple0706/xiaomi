package com.xiao.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.util.DButil;
import com.xiaomi.mapper.GoodMapper;
import com.xiaomi.pojo.vo.Good;

public class GoodService {

	public List<Good> selectGoodList(){
		SqlSession sqlSession = DButil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		List<Good> selectByExample = mapper.selectByExample(null);
		sqlSession.close();
		return selectByExample;
	}
}

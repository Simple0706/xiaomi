package com.xiao.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.util.DButil;
import com.xiaomi.mapper.XmorderMapper;
import com.xiaomi.pojo.vo.Xmorder;

public class XmOrderService {

	public List<Xmorder> selectAll(){
		SqlSession sqlSession = DButil.getSqlSession();
		XmorderMapper mapper = sqlSession.getMapper(XmorderMapper.class);
		List<Xmorder> selectByExample = mapper.selectByExample(null);
		return selectByExample;
	}
}

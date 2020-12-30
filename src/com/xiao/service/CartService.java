package com.xiao.service;

import java.util.List;


import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.util.DButil;
import com.xiaomi.mapper.CartMapper;
import com.xiaomi.mapper.GoodMapper;
import com.xiaomi.pojo.vo.Cart;
import com.xiaomi.pojo.vo.Good;
import com.xiaomi.pojo.vo.GoodExample;
import com.xiaomi.pojo.vo.GoodExample.Criteria;

public class CartService {
	
	public List<Cart> selectCartList(){
		SqlSession sqlSession = DButil.getSqlSession();
		CartMapper mapper = sqlSession.getMapper(CartMapper.class);
		List<Cart> selectByExample = mapper.selectByExample(null);
		sqlSession.close();
		return selectByExample;
		
	}
	public Good selectGoodById(int id){
		SqlSession sqlSession = DButil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		Good selectByPrimaryKey = mapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}
}

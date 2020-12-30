package com.xiao.service;

import java.util.List;



import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.util.DButil;
import com.xiaomi.mapper.CartMapper;
import com.xiaomi.mapper.GoodMapper;
import com.xiaomi.pojo.vo.Cart;
import com.xiaomi.pojo.vo.CartExample;
import com.xiaomi.pojo.vo.CartExample.Criteria;
import com.xiaomi.pojo.vo.Good;



public class CartService {
	//根据id所有购物车
	public List<Cart> selectCartList(int uid){
		SqlSession sqlSession = DButil.getSqlSession();
		CartMapper mapper = sqlSession.getMapper(CartMapper.class);
		CartExample cartExample = new CartExample();
		Criteria createCriteria = cartExample.createCriteria();
		createCriteria.andUidEqualTo(uid);
		List<Cart> selectByExample = mapper.selectByExample(cartExample);
		sqlSession.close();
		return selectByExample;
		
	}
	//根据Cart_id查询所有Good；
	public Good selectGoodById(int id){
		SqlSession sqlSession = DButil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		Good selectByPrimaryKey = mapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}
}
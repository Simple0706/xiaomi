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
	//根据id查询所有购物车
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
	//根据Cart_id查询Good；
	public Good selectGoodById(int id){
		SqlSession sqlSession = DButil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		Good selectByPrimaryKey = mapper.selectByPrimaryKey(id);
		sqlSession.commit();
		sqlSession.close();
		return selectByPrimaryKey;
	}
	//添加数据
	public int insert(Cart cart){
		SqlSession sqlSession = DButil.getSqlSession();
		CartMapper mapper = sqlSession.getMapper(CartMapper.class);
		int insert = mapper.insertSelective(cart);
		if(insert>0){
			sqlSession.commit();
			sqlSession.close();
			return 1;
		}else{
			sqlSession.rollback();
			sqlSession.close();
		return 0;
		}
	}
	//修改表数据
	public int updataGood_Num(Cart cart,int id){
		SqlSession sqlSession = DButil.getSqlSession();
		CartMapper mapper = sqlSession.getMapper(CartMapper.class);
		CartExample cartExample = new CartExample();
		Criteria createCriteria = cartExample.createCriteria();
		createCriteria.andPreIdEqualTo(id);
		mapper.updateByExampleSelective(cart, cartExample);
		sqlSession.commit();
		sqlSession.close();
		return 1;
	}
	//根据uid判断购物车是否有数据
	public Cart isCreadata(int goodid,int uid){
		SqlSession sqlSession = DButil.getSqlSession();
		CartMapper mapper = sqlSession.getMapper(CartMapper.class);
		CartExample cartExample = new CartExample();
		Criteria createCriteria = cartExample.createCriteria();
		createCriteria.andGoodIdEqualTo(goodid);
		createCriteria.andUidEqualTo(uid);
		List<Cart> selectByExample = mapper.selectByExample(cartExample);
		Cart cart = selectByExample.get(0);
		sqlSession.commit();
		sqlSession.close();
		if(selectByExample.size()>0){
			return cart;
		}else{
			return null;
		}
	}
	
		//根据id删除uid购物车物品
		public int deleteCartById(int id,int uid){
			SqlSession sqlSession = DButil.getSqlSession();
			CartMapper mapper = sqlSession.getMapper(CartMapper.class);
			CartExample cartExample = new CartExample();
			Criteria createCriteria = cartExample.createCriteria();
			createCriteria.andPreIdEqualTo(id);
			createCriteria.andUidEqualTo(uid);
			int deleteByExample = mapper.deleteByExample(cartExample);
			sqlSession.commit();
			sqlSession.close();
			return deleteByExample;
		}
		//根据cart_id修改个数
		public int updateCartCartByCartId(Cart cart,int uid){
			SqlSession sqlSession = DButil.getSqlSession();
			CartMapper mapper = sqlSession.getMapper(CartMapper.class);
			CartExample cartExample = new CartExample();
			Criteria createCriteria = cartExample.createCriteria();
			createCriteria.andUidEqualTo(uid);
			int updateByExampleSelective = mapper.updateByExampleSelective(cart, cartExample);
			sqlSession.commit();
			sqlSession.close();
			return updateByExampleSelective;
		}
		
		public Cart selectGoodByUidAndGoodid(int uid, int goodid) {
			SqlSession sqlSession = DButil.getSqlSession();
			CartMapper mapper = sqlSession.getMapper(CartMapper.class);
			CartExample cartExample = new CartExample();
			Criteria createCriteria = cartExample.createCriteria();
			createCriteria.andGoodIdEqualTo(goodid);
			createCriteria.andUidEqualTo(uid);
			List<Cart> selectByExample = mapper.selectByExample(cartExample);
			sqlSession.close();
			if(selectByExample.size()>0){
				sqlSession.commit();
				sqlSession.close();
				return selectByExample.get(0);
			}else{
				sqlSession.commit();
				sqlSession.close();
				return null;
			}
		}
		public int updateCartByUidAndPreId(Cart cart, int uid, int preId) {
			SqlSession sqlSession = DButil.getSqlSession();
			CartMapper mapper = sqlSession.getMapper(CartMapper.class);
			CartExample cartExample = new CartExample();
			Criteria createCriteria = cartExample.createCriteria();
			createCriteria.andUidEqualTo(uid);
			createCriteria.andPreIdEqualTo(preId);
			int updateByExampleSelective = mapper.updateByExampleSelective(cart, cartExample);
			sqlSession.commit();
			sqlSession.close();
			return updateByExampleSelective;
			
		}
}

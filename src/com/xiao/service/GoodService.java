package com.xiao.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.util.DButil;
import com.xiaomi.mapper.GoodMapper;
import com.xiaomi.pojo.vo.Good;
import com.xiaomi.pojo.vo.GoodExample;
import com.xiaomi.pojo.vo.GoodExample.Criteria;

public class GoodService {
	//查询所以手机
	public List<Good> selectGoodList(){
		SqlSession sqlSession = DButil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		List<Good> selectByExample = mapper.selectByExample(null);
		
		sqlSession.close();
		return selectByExample;
	}
	//根据id查询手机
	public List<Good> selectGoodByName(String name){
		SqlSession sqlSession = DButil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		GoodExample goodExample = new GoodExample();
		Criteria createCriteria = goodExample.createCriteria();
		createCriteria.andGoodNameEqualTo(name);
		List<Good> selectByExample = mapper.selectByExample(goodExample);
		
		sqlSession.close();
		return selectByExample;
		
		
	}
	//根据id模糊查询手机
	public List<Good> selectGoodLikeByName(String name){
		SqlSession sqlSession = DButil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		GoodExample goodExample = new GoodExample();
		Criteria createCriteria = goodExample.createCriteria();
		createCriteria.andGoodNameLike(name);
		List<Good> selectByExample = mapper.selectByExample(goodExample);
		
		sqlSession.close();
		return selectByExample;
		
		
	}
	public Good selectGoodByNameAndType(String name, String type,String color) {
		SqlSession sqlSession = DButil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		GoodExample goodExample = new GoodExample();
		Criteria createCriteria = goodExample.createCriteria();
		createCriteria.andGoodNameEqualTo(name);
		createCriteria.andGoodTypeEqualTo(type);
		if(color!=null){
		createCriteria.andGoodColorEqualTo(color);
		}
		List<Good> selectByExample = mapper.selectByExample(goodExample);
		
		sqlSession.close();
		if(selectByExample!=null){
			System.out.println(selectByExample);
			Good good11 = selectByExample.get(0);
			return good11;
		}
		return null;
	}
	
	
}

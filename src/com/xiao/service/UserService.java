package com.xiao.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.util.DButil;
import com.xiaomi.mapper.UsersMapper;
import com.xiaomi.pojo.vo.Users;
import com.xiaomi.pojo.vo.UsersExample;
import com.xiaomi.pojo.vo.UsersExample.Criteria;

public class UserService {

	//注册
	public int register(Users user){
		SqlSession sqlSession = DButil.getSqlSession();
		UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
		UsersExample usersExample = new UsersExample();
		Criteria createCriteria = usersExample.createCriteria();
		createCriteria.andUsernameEqualTo(user.getUsername());
		List<Users> selectByExample = mapper.selectByExample(usersExample);
		
		if(selectByExample.size()>0){
			return 0;
		}else{
			mapper.insertSelective(user);
			sqlSession.commit();
		}
		sqlSession.close();
		
		return 1;
	}
	//登录
	public Users login(Users user){
		SqlSession sqlSession = DButil.getSqlSession();
		UsersExample usersExample = new UsersExample();
		UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
		Criteria createCriteria = usersExample.createCriteria();
		createCriteria.andUsernameEqualTo(user.getUsername());
		List<Users> selectByExample = mapper.selectByExample(usersExample);
		if(selectByExample.size()>0){
			return selectByExample.get(0);
		}else{
			return null;
		}
	}
	
}

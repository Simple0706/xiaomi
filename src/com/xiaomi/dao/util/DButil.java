package com.xiaomi.dao.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DButil {
	static SqlSessionFactory build=null;
	static{
		String Configxml = "mybatis-config.xml";
		InputStream resourceAsStream=null;
		try {
			resourceAsStream= Resources.getResourceAsStream(Configxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 build = new SqlSessionFactoryBuilder().build(resourceAsStream);
	}
	
	public static SqlSession getSqlSession(){
		return build.openSession();
	}
}

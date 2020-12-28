import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xiaomi.dao.util.DButil;
import com.xiaomi.mapper.UsersMapper;
import com.xiaomi.pojo.vo.Users;
import com.xiaomi.pojo.vo.UsersExample;
import com.xiaomi.pojo.vo.UsersExample.Criteria;


public class Test {

	@org.junit.Test
	public void test() throws IOException {
		SqlSession sqlSession = DButil.getSqlSession();
		UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
		UsersExample example = new 	UsersExample();
		Criteria createCriteria = example.createCriteria();
		
		createCriteria.andPasswordNotBetween("0", "1");
		List<Users> selectByExample = mapper.selectByExample(example);
		System.out.println(selectByExample);
		sqlSession.close();
	}

}

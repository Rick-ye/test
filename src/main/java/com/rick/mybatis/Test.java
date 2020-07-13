package com.rick.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws IOException {
        //1.读取配置的xml文件配置构建一个SqlSessionFactoryBuilder，然后build一个SqlSessionFactory
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(is);

        //2.在factory中创建一个sqlsession会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        List<User> users = sqlSession.selectList("com.rick.mybatis.UserMapper.selectByPrimaryKey", map);
        //UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //List<User> users = userMapper.selectByPrimaryKey(map);
        System.out.println(">>>>>>>  " + users.get(0).getName());
    }
}

package com.rick.mybatis;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	public List<User> selectByPrimaryKey(Map<String, Object> map);
 
}

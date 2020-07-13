package com.rick.mybatis;

/**
 * TODO
 * 
 * @author 作者 E-mail:2332999366@qq.com
 * @version 创建时间：2017年12月21日 上午9:01:17
 */
public class Student {
 
	private Integer id;
	private String name;
	private Integer teacherId;
	private String className;
	private Teacher teacher;
	
 
	public Teacher getTeacher() {
		return teacher;
	}
 
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
 
	public Integer getId() {
		return id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public Integer getTeacherId() {
		return teacherId;
	}
 
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
 
	public String getClassName() {
		return className;
	}
 
	public void setClassName(String className) {
		this.className = className;
	}
 
	@Override
	public String toString() {
		return "{id:"+this.id+",name:"+this.name+",className:"+this.className+",teacherId:"+this.teacherId+"}";
	}
} 
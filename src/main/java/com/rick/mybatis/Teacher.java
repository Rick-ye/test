package com.rick.mybatis;

import java.util.List;
 
/**
 * TODO
 * @version 创建时间：2017年12月21日 上午9:02:45
 */
public class Teacher {
 
	private Integer id;
	private String name;
	private String className;
	private List<Student> students;
 
	public List<Student> getStudents() {
		return students;
	}
 
	public void setStudents(List<Student> students) {
		this.students = students;
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
 
	public String getClassName() {
		return className;
	}
 
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"id=" + id +
				", name='" + name + '\'' +
				", className='" + className + '\'' +
				", students=" + students +
				'}';
	}
}
package com.code.dao;

import java.util.List;

import com.code.entity.Type;

public interface ITypeDAO {
	/**
	 * 所有的父类型
	 * 
	 * @return
	 */
	List<Type> allParentType();

	/**
	 * 指定父类下的所有子类
	 * 
	 * @param parentId
	 * @return
	 */
	List<Type> allChildType(int parentId);

	/**
	 * 查找子类型的父类型
	 * 
	 * @param childType
	 * @return
	 */
	Type parentType(int childType);

	/**
	 * 是否为父类型
	 * 
	 * @param typeId
	 * @return
	 */
	boolean isParentType(int typeId);

	/**
	 * 根据名称找Type
	 * 
	 * @param name
	 * @return
	 */
	Type findByName(String name);
	
	/**
	 * 根据ID找Type
	 * 
	 * @param name
	 * @return
	 */
	Type findById(int id);

	/**
	 * 添加一个类型
	 * 
	 * @param type
	 * @return
	 */
	int addType(Type type);
	
	String findName(int id);
}

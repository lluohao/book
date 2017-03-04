package com.code.service;

import java.util.List;

import com.code.entity.Type;

public interface ITypeService {
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
	 * 添加一个类型
	 * @param type
	 * @return
	 */
	int addType(Type type);
	Type findById(int typeId);
	
	String getLocation(String style,int typeId);
}

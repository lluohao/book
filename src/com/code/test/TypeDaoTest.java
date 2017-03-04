package com.code.test;

import java.util.List;

import com.code.dao.ITypeDAO;
import com.code.dao.impl.TypeDaoImpl;
import com.code.entity.Type;

public class TypeDaoTest {
	public static void main(String[] args) {
		TypeDaoImpl dao=new TypeDaoImpl();
//		Type type1=new Type();
//		type1.setParentTypeId(1);
//		type1.setTypeName("言情");
//		int i=dao.addType(type1);
//		System.out.println(i);
//		List<Type> types=dao.allChildType(1);
//		System.out.println(types.size());
//		List<Type> types=dao.allParentType();
//		System.out.println(types.size());
//		Type type=dao.findById(1);
//		System.out.println(type.getTypeName());
//		System.out.println(dao.isParentType(24));s
//		Type type=dao.findByName("网络小说");
//		System.out.println(type.getTypeId());
//		String urls=dao.getLoaction(24);
//		System.out.println(urls);
		System.out.println(dao.parentType(24).getTypeName());
		System.out.println(dao.findName(24));
		
	}
}

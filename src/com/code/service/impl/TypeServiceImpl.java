package com.code.service.impl;

import java.util.List;

import com.code.dao.ITypeDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Type;
import com.code.service.ITypeService;

public class TypeServiceImpl implements ITypeService {
	public List<Type> allParentType() {
		ITypeDAO dao = DaoFactory.newInstance().newTypeDAO();
		List<Type> types = dao.allParentType();
		if (types == null || types.size() == 0) {
			throw new RuntimeException("没有找到！！！");
		}
		return types;
	}

	public List<Type> allChildType(int parentId) {
		ITypeDAO dao = DaoFactory.newInstance().newTypeDAO();
		List<Type> types = dao.allChildType(parentId);
		if (types == null || types.size() == 0) {
			throw new RuntimeException("没有找到！！！");
		}
		return types;
	}

	public Type parentType(int childType) {
		ITypeDAO dao = DaoFactory.newInstance().newTypeDAO();
		Type type = dao.parentType(childType);
		if (type == null || type.getParentTypeId() == 0) {
			throw new RuntimeException("没有父类！！！");
		}
		return type;
	}

	public boolean isParentType(int typeId) {
		ITypeDAO dao = DaoFactory.newInstance().newTypeDAO();
		return dao.isParentType(typeId);
	}

	public Type findByName(String name) {
		ITypeDAO dao = DaoFactory.newInstance().newTypeDAO();
		Type type = dao.findByName(name);
		if (type == null || type.getParentTypeId() == 0) {
			throw new RuntimeException("没有该类型！！！");
		}
		return type;
	}

	public int addType(Type type) {
		ITypeDAO dao = DaoFactory.newInstance().newTypeDAO();
		return dao.addType(type);
	}

	public Type findById(int typeId) {
		ITypeDAO dao = DaoFactory.newInstance().newTypeDAO();
		Type type = dao.findById(typeId);
		if (type == null || type.getParentTypeId() == 0) {
			throw new RuntimeException("没有该类型！！！");
		}
		return type;
	}

	@Deprecated
	@Override
	public String getLocation(String style, int typeId) {
		ITypeDAO dao = DaoFactory.newInstance().newTypeDAO();
		String childName = null;
		String parentName = null;
		String url = "F:\\data\\" + style;
		if (typeId > 0) {
			childName = dao.findName(typeId);
			Type t = dao.parentType(typeId);
			if (t != null) {
				parentName = t.getTypeName();
			}
		}
		if (parentName != null && childName != null) {
			url = "F:\\data\\" + style + "\\" + parentName + "\\" + childName;
		}
		if (parentName.equals("父类")) {
			url = "F:\\data\\" + style + "\\" + childName;
		}
		return url;
	}

}

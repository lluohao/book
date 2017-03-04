package com.code.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.code.entity.Type;
import com.code.service.ITypeService;
import com.code.service.fact.ServiceFactory;

public class TypeUtil {
	public static Type parserType(ResultSet rs) throws SQLException {
		Type type = new Type();
		type.setParentTypeId(rs.getInt("type_parent"));
		type.setTypeId(rs.getInt("type_id"));
		type.setTypeName(rs.getString("type_name"));
		return type;
	}

	public static int machType(String str) {
		ITypeService service = ServiceFactory.getInstance().newTypeService();
		List<Type> fs = service.allChildType(-1);
		for (Type parent : fs) {
			if(parent.getTypeId()==25){
				continue;
			}
			List<Type> types = service.allChildType(parent.getTypeId());
			for (Type type : types) {
				if (str.contains(type.getTypeName())) {
					return type.getTypeId();
				}
			}
		}
		return 25;
	}
}

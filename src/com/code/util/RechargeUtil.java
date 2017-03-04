package com.code.util;

import java.sql.ResultSet;

import com.code.entity.Recharge;

public class RechargeUtil {
	public static Recharge parseRecharge(ResultSet rs) throws Exception {
		Recharge recharge = new Recharge();
		recharge.setCount(rs.getInt("recharge_count"));
		recharge.setTime(rs.getTimestamp("recharge_time"));
		recharge.setUse(rs.getInt("recharge_use") == 1);
		recharge.setUserId(rs.getInt("recharge_userid"));
		recharge.setId(rs.getInt("recharge_id"));

		return recharge;

	}

}

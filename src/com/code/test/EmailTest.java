package com.code.test;

import org.junit.Test;

import com.code.util.EmailUtil;

public class EmailTest {
	@Test
	public void testIsEmail() throws Exception {
		String email = "840324@qqcom";
		System.out.println(EmailUtil.isEmail(email));
	}
}

package com.sports.meetup.user;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sports.meetup.user.util.EncryptionUtil;

public class EncryptionUtilTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void TestDecrypte() {
		String encText = EncryptionUtil.getDecryptString("rAeUfG48L60=");
		System.out.println("解密后的值为："+ encText);
	}
	
}

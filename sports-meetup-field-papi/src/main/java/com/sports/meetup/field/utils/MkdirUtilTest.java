package com.sports.meetup.field.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class MkdirUtilTest {

	@Test
	public void test() {
		System.out.println(MkdirUtil.getDirname(System.currentTimeMillis()));
	}

}

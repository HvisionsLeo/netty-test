package com.unitill.util;

import org.apache.commons.lang.RandomStringUtils;

public class IDGeneratorUtils {
	public static String genId() {
		return RandomStringUtils.randomAlphanumeric(32);
	}
}

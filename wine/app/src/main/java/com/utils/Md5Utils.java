package com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @file Md5Utils.java
 * @create 2013-5-27 上午11:22:36
 * @author Jacky.Lee
 * @description TODO A utility class for computing MD5 hashes.
 */
public class Md5Utils {

	private Md5Utils() {
	}

	/**
	 * Return a hash according to the MD5 algorithm of the given String.
	 * 
	 * @param s
	 *            The String whose hash is required
	 * @return The MD5 hash of the given String
	 */
	public static String md5ForString(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToMd5String(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}

		return cacheKey;
	}

	public static String hashKeyForString(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}

		return cacheKey;
	}

	private static String bytesToMd5String(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			final int b = bytes[i] & 255;
			if (b < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(b));
		}
		return sb.toString();
	}

	private static String bytesToHexString(byte[] bytes) {
		// http://stackoverflow.com/questions/332079
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}
}

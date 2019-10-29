package com.xinke.management.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 
 * @author unknown
 */
public class MD5Encoder {

	/**
	 * Byte2hex.
	 * 
	 * @param b
	 *           需要加密的byte数组
	 * @return 加密后得到的字符串
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		// 2014年6月17日修改，用于与审批系统对接
		// return hs.toUpperCase();
		return hs.toLowerCase();
	}

	/**
	 * 加密方法.
	 * 
	 * @param input
	 *            需要加密的字符串
	 * @return 加密后得到的字符串
	 */
	public static String encode(String input) {
		byte[] digesta = null;
		try {
			MessageDigest alga = MessageDigest.getInstance("MD5");
			alga.update(input.getBytes());
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			// throw new SystemException(ErrorCode.UNKNOW_ERROR, e);
		}
		return byte2hex(digesta);
	}
	
	/**
	 * self test
	 *@name    中文名称
	 *@Description 相关说明	 
	 *@Time    创建时间:2015-9-23下午4:49:25
	 *@param plainText
	 *@return  String 
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public String Md5(String plainText) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            // System.out.println("result: " + buf.toString());// 32位的加密
            // System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
        }
        catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buf.toString();
    }

	private static String byteArrayToHexString(byte b[])
	{
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	private static final String hexDigits[] =
			{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

	public static String MD5Encode(String origin, String charsetname)
	{
		String resultString = null;
		try
		{
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		}
		catch (Exception exception)
		{
		}
		return resultString;
	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		System.out.println(encode("123456"));
	}

}

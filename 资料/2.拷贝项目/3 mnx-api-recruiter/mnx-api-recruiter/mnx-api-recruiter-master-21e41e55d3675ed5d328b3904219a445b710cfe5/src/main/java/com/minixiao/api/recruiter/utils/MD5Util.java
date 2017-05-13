package com.minixiao.api.recruiter.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

/**
 * @Description: MD5工具
 * @author: chenyongqiang
 * @Date: 2015年4月9日
 * @version: 1.0
 */

public class MD5Util {

  //编码方式
  public static final String CHARSET_UTF8 = "UTF-8";
  //加密方式
  public static final String ENCRYPT_TYPE_MD5 = "MD5";
  //加密迭代次数
  public static final int HASH_ITERATIONS = 2;

  /**
   * @param saltByte 盐
   * @param orl      orl
   * @Definition: 获取字符串的MD5散列算法后的字符数 .
   * @author: chenyongqiang
   * @Date: 2015年4月9日
   */
  public static String getEncryptString(String orl, byte[] saltByte) {
    try {
      //md5散列算法
      byte[] orlByte = orl.getBytes(CHARSET_UTF8);
      MessageDigest md = MessageDigest.getInstance(ENCRYPT_TYPE_MD5);
      md.update(saltByte);
      md.update(orlByte);
      byte[] digestByte = md.digest();
      //把随机盐和md5散列算法后得到的byte整合，防止数据库中的密码泄露，就是被一一比对。
      byte[] dsByte = new byte[digestByte.length + saltByte.length];
      System.arraycopy(saltByte, 0, dsByte, 0, saltByte.length);
      System.arraycopy(digestByte, 0, dsByte, saltByte.length, digestByte.length);
      //base64编码，原因是byte数组中有一些byte变成字符串之后无法显示，有两种做法，一种是base64编码，另外一种是把byte转换成16位进制
      BASE64Encoder encoder = new BASE64Encoder();
      return encoder.encodeBuffer(dsByte);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return null;
  }

  /**
   * @param orl orl
   * @Definition: 获取字符串的MD5散列算法后的字符数(随机盐) .
   * @author: chenyongqiang
   * @Date: 2015年4月9日
   */
  public static String getEncryptString(String orl) {
    //获取随机盐
    Random random = new Random();
    byte[] saltByte = new byte[8];
    random.nextBytes(saltByte);
    return getEncryptString(orl, saltByte);
  }

  /**
   * @param orl      没进行过MD5加密的字符串
   * @param dest     已进行过MD5加密的字符串
   * @param saltByte 盐
   * @Definition: .
   * @author: chenyongqiang
   * @Date: 2015年4月9日
   */
  public static boolean compare(String orl, String dest, byte[] saltByte) {
    try {
      //base64解码
      BASE64Decoder decoder = new BASE64Decoder();
      byte[] destByte = decoder.decodeBuffer(dest);
      //获取随机盐
      System.arraycopy(destByte, 0, saltByte, 0, saltByte.length);
      //md5散列算法
      byte[] orlByte = orl.getBytes(CHARSET_UTF8);
      MessageDigest md = MessageDigest.getInstance(ENCRYPT_TYPE_MD5);
      md.update(saltByte);
      md.update(orlByte);
      byte[] digestByte = md.digest();
      //获取dest中的md5散列byte
      byte[] destDigestByte = new byte[destByte.length - saltByte.length];
      System.arraycopy(destByte, saltByte.length, destDigestByte, 0, destDigestByte.length);
      return Arrays.equals(digestByte, destDigestByte);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return false;
  }

  /**
   * @param orl  没进行过MD5加密的字符串
   * @param dest 已进行过MD5加密的字符串
   * @return boolean
   * @Definition: 比较两个经过MD5加密的字符数(随机盐) .
   * @author: chenyongqiang
   */
  public static boolean compare(String orl, String dest) {
    try {
      //base64解码
      BASE64Decoder decoder = new BASE64Decoder();
      byte[] destByte = decoder.decodeBuffer(dest);
      //获取随机盐
      byte[] saltByte = new byte[8];
      return compare(orl, dest, saltByte);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return false;
  }

  /**
   * @Definition: .
   * @author: Henry
   * @Date: 2015年4月9日
   */
  public static final String str2MD5(String inStr) {
    char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
        'f'};
    try {
      byte[] strTemp = inStr.getBytes("UTF-8");
      MessageDigest mdTemp = MessageDigest.getInstance("MD5");
      mdTemp.update(strTemp);
      byte[] md = mdTemp.digest();
      int length = md.length;
      char[] str = new char[length * 2];
      int tmp = 0;
      for (int i = 0; i < length; i++) {
        byte byte0 = md[i];
        str[tmp++] = hexDigits[byte0 >>> 4 & 0xf];
        str[tmp++] = hexDigits[byte0 & 0xf];
      }
      return new String(str);
    } catch (Exception ex) {
      return null;
    }
  }


  /**
   * 测试主方法 .
   *
   * @param args args
   * @throws UnsupportedEncodingException UnsupportedEncodingException
   */
   public static void main(String[] args) throws UnsupportedEncodingException {
    String pwd = "123456";
    System.out.println(MD5Util.getEncryptString(pwd));
    System.out.println(MD5Util.compare("123456", "mRPTemC8KsvDylzEwbaU/thry21N5jnN"));
  }

  /**
   * 返回编码后的字节数组.
   *
   * @param source source
   * @return byte
   */
  public static byte[] encode2bytes(String source) {
    byte[] result = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.reset();
      md.update(source.getBytes("UTF-8"));
      result = md.digest();
    } catch (NoSuchAlgorithmException ex) {
      ex.printStackTrace();
    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    }
    return result;
  }

  /**
   * .
   *
   * @param source source
   * @return String
   * @author yangxiaolong 将源字符串使用MD5加密为32位16进制数
   */
  public static String encode2hex(String source) {
    byte[] data = encode2bytes(source);
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < data.length; i++) {
      String hex = Integer.toHexString(0xff & data[i]);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

  /**
   * .
   *
   * @param unknown 待验证的字符串
   * @param okHex   使用MD5加密过的16进制字符串
   * @return 匹配返回true，不匹配返回false
   * @author yangxiaolong 验证字符串是否匹配
   */
  public static boolean validate(String unknown, String okHex) {
    return okHex.equals(encode2hex(unknown));
  }
}

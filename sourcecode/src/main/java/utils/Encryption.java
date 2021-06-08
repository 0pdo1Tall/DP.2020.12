package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	
  /**
  *  Clean Code: Cần thay đổi tên md5 => encryptMd5 
  */
	/**
     * Return a {@link String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @param message - plain text as {@link String String}.
     * @return cipher text as {@link String String}.
     */
  public static String encryptMd5(String message) {
      String digest = null;
      try {
          // Clean Code: change md to messageDigest here
          MessageDigest messageDigest = MessageDigest.getInstance("MD5");
          byte[] hash = messageDigest.digest(message.getBytes(StandardCharsets.UTF_8));
          // converting byte array to Hexadecimal String
          // Clean Code: change sb to digestStringBuilder
          StringBuilder digestStringBuilder = new StringBuilder(2 * hash.length);
          for (byte b : hash) {
              digestStringBuilder.append(String.format("%02x", b & 0xff));
          }
          digest = digestStringBuilder.toString();
      } catch (NoSuchAlgorithmException ex) {
          Utils.getLogger(Utils.class.getName());
          digest = "";
      }
      return digest;
      
      // Data coupling, chỉ truyền đủ dữ liệu vào để xử lý và trả về kết quả
  }
}
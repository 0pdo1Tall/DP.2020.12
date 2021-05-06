package utils;

public class Encryption {
  /**
  *  Clean Code: Cần thay đổi tên md5 => encryptMd5 
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
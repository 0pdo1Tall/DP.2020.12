package controller;

import common.exception.ExpiredSessionException;
import common.exception.FailLoginException;
import dao.user.UserDAO;
import entity.user.User;
import utils.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;



/**
 * @author
 */

//SOLID: vi phạm nguyên lí ISP, vì nó kế thừa lớp BaseController nhưng không dùng lại các hàm trong lớp đó 

public class AuthenticationController extends BaseController {
	
	/**
	 * Singleton: lý do quản lý mainUser, expiredTime,... chỉ có duy nhất một đối tượng (khai báo static bên SessionInformation),
	 *  nên chỉ cần 1 controller để quản lý, tạo thêm cũng không thể tăng hiệu suất sử dụng
	 */
	
	private static AuthenticationController _authenticationControllerInstance;
	
	private AuthenticationController() {

	}
	
	public static synchronized AuthenticationController getInstance() {
		if (_authenticationControllerInstance == null) {
			_authenticationControllerInstance = new AuthenticationController();
		}
		return _authenticationControllerInstance;
	}
	
	/**
	 * 	SOLID: Vi phạm nguyên lý SRP. Thực hiện hai nhiệm vụ, một nhiệm vụ là điều hướng Authentication.
	 * 	Một nhiệm vụ là mã hóa md5.
	 */
	
	/**
	 *  SOLID: Vi phạm nguyên lý DIP. Có phụ thuộc vào phương thức mã hóa md5.
	 *  Đáng lẽ nên phụ thuộc vào một lớp abstract để thực hiện nhiệm vụ mã hóa, sau đó cho kế thừa và md5 là một
	 *  các lớp kế thừa để trong tương lai nếu có thay đổi phương thức mã hóa thì cũng không ảnh hướng đến class này
	 *  
	 *  Vì cùng lý do như trên
	 *  SOLID: Vi phạm nguyên lý OCP.
	 */
	
	/**
	 * Coincidental cohesion, do có phương thức md5(String message) dùng để mã hóa chuỗi theo hàm băm md5
	 * không liên quan đến các phương thức khác phục vụ cho mục đích quản lý trong lớp AuthenticationController
	 * không phục vụ cho tính năng của lớp này
	 */
	
    public boolean isAnonymousSession() {
        try {
            getMainUser();
            return false;
        } catch (Exception ex) {
            return true;
        }
        
        // Data coupling, gọi đến phương thức khác
    }

    public User getMainUser() throws ExpiredSessionException {
        if (SessionInformation.mainUser == null || SessionInformation.expiredTime == null || SessionInformation.expiredTime.isBefore(LocalDateTime.now())) {
            logout();
            throw new ExpiredSessionException();
            
            // Data coupling, chỉ dùng dữ liệu cần thiết
        } else return SessionInformation.mainUser.cloneInformation();
    }

    public void login(String email, String password) throws Exception {
        try {
            User user = new UserDAO().authenticate(email, md5(password));
            if (Objects.isNull(user)) throw new FailLoginException();
            SessionInformation.mainUser = user;
            SessionInformation.expiredTime = LocalDateTime.now().plusHours(24);
            
            // Content coupling, sửa trực tiếp vào dữ liệu của module khác
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }

    public void logout() {
        SessionInformation.mainUser = null;
        SessionInformation.expiredTime = null;
        
        // Content coupling, sửa trực tiếp vào dữ liệu của module khác
    }

    /**
     * Return a {@link String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @param message - plain text as {@link String String}.
     * @return cipher text as {@link String String}.
     */
    private String md5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Utils.getLogger(Utils.class.getName());
            digest = "";
        }
        return digest;
        
        // Data coupling, chỉ truyền đủ dữ liệu vào để xử lý và trả về kết quả
    }

}

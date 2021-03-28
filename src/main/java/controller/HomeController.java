package controller;

import java.sql.SQLException;
import java.util.List;

import dao.media.MediaDAO;

/**
 * This class controls the flow of events in homescreen
 * @author nguyenlm
 */

//SOLID: vi phạm nguyên lí ISP, vì nó kế thừa lớp BaseController nhưng không dùng lại các hàm trong lớp đó 
public class HomeController extends BaseController {
	
	/**
	 * Singleton: mỗi lần quay về HomeScreen ta lại tạo sử dụng một đối tượng thuộc lớp HomeController để quản lý,
	 * thực tế chỉ cần 1 controller quản lý, chỉ có một màn hình HomeScreen, do đó không cần nhiều controller để quản lý
	 */

	private static HomeController _homeControllerInstance;
	
	private HomeController() {
		
	}
	
	public static synchronized HomeController getInstance() {
		if (_homeControllerInstance == null) {
			_homeControllerInstance = new HomeController();
		}
		return _homeControllerInstance;
	}
	
    /**
     * this method gets all Media in DB and return back to home to display
     * @return List[Media]
     * @throws SQLException
     */
    public static List getAllMedia() throws SQLException{
        return new MediaDAO().getAllMedia();
        
        // Data coupling, sử dụng phương thức để lấy dữ liệu càn thiết
    }
}

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
     * this method gets all Media in DB and return back to home to display
     * @return List[Media]
     * @throws SQLException
     */
    public static List getAllMedia() throws SQLException{
        return new MediaDAO().getAllMedia();
    }
}

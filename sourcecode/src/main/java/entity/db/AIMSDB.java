package entity.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import java.sql.Connection;
import utils.*;

// Singleton: DB duoc coi nhu mot doi tuong lon, duy nhat, global(dung de tao connection) nen duoc dat la Singleton
public class AIMSDB {
    
	/**
	 * Clean code: for String constants --> create 2 static final String variable to get name more informative
	 * remove the local variable databaseURL in getConnection() because it no longer needed 
	 */
	private static final String CLASS_NAME = "org.sqlite.JDBC";
	private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/assets/db/aims.db";
	
    private static AIMSDB db;
    private AIMSDB(){}
    public static AIMSDB getInstance() {
        if(db == null) db = new AIMSDB();
        return db;
    }

	private static final Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;
	// TODO: refactor Utils -> limit connections
    public static Connection getConnection() { //communicational cohesion: các phương thức dùng dung thuộc tính() {
        // Clean Code: change url to databaseURL
        if (connect != null) return connect;
        try {
			Class.forName(CLASS_NAME);
            connect = DriverManager.getConnection(DATABASE_URL);
            LOGGER.info("Connect database successfully");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        } 
        return connect;
    }


    public static void main(String[] args) {
        AIMSDB.getInstance().getConnection();
    }

    //function cohesion do hàm main sử dụng //communicational cohesion: các phương thức dùng dung thuộc tính
}

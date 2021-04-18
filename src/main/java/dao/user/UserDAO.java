package dao.user;

import entity.db.AIMSDB;
import entity.media.Book;
import entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * @author
 */
public class UserDAO {

    // Clean Code: Method Factoring - Change query string to string with placeholder
    private String getUserAndEmailAndPasswordQuery = "SELECT * FROM User " +
            "where email = '" + "%s" + "' and encrypted_password = '" + "%s" + "'";

	/**
	 * Clean code: Many level of Abstraction in method
	 * Need to separate code to get query, code to create result to other methods
	 * Create two methods: getUserAndEmailAndPasswordQuery and getUserResult
	 */
    // Data Coupling
    // Coincidental Cohesion: authenticate function must be in its own module
    public User authenticate(String email, String encryptedPassword) throws SQLException {
        // Clean Code: change sql to getUserAndEmailAndPasswordQuery, res userResultSe
        String getUserAndEmailAndPasswordQuery = getUserAndEmailAndPasswordQuery(email, encryptedPassword);
        ResultSet userResultSet =  AIMSDB.getInstance().getConnection().createStatement().executeQuery(getUserAndEmailAndPasswordQuery);
        if(userResultSet.next()) {
            return getUserResult(userResultSet);
        } else {
            throw new SQLException();
        }
    }
    
    public String getUserAndEmailAndPasswordQuery(String email, String encryptedPassword) {
    	return "SELECT * FROM User " +
                "where email = '" + email + "' and encrypted_password = '" + encryptedPassword + "'";
    }
    
    public User getUserResult(ResultSet userResultSet) throws SQLException {
    	return new User(
                userResultSet.getInt("id"),
                userResultSet.getString("name"),
                userResultSet.getString("email"),
                userResultSet.getString("address"),
                userResultSet.getString("phone")
        );
    }
}

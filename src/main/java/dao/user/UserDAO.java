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

    // Data Coupling
    // Coincidental Cohesion: authenticate function must be in its own module
    public User authenticate(String email, String encryptedPassword) throws SQLException {
        // Clean Code: change sql to getUserAndEmailAndPasswordQuery, res userResultSet
        String getUserAndEmailAndPasswordQuery = "SELECT * FROM User " +
                "where email = '" + email + "' and encrypted_password = '" + encryptedPassword + "'";
        ResultSet userResultSet =  AIMSDB.getInstance().getConnection().createStatement().executeQuery(getUserAndEmailAndPasswordQuery);
        if(userResultSet.next()) {
            return new User(
                    userResultSet.getInt("id"),
                    userResultSet.getString("name"),
                    userResultSet.getString("email"),
                    userResultSet.getString("address"),
                    userResultSet.getString("phone")
            );
        } else {
            throw new SQLException();
        }
    }
}

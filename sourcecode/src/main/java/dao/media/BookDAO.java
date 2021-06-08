package dao.media;

import entity.db.AIMSDB;
import entity.media.Book;
import entity.media.BookFactory;
import entity.media.Media;
import entity.media.MediaFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * @author
 */
public class BookDAO extends MediaDAO {
    // Clean Code: Method Refactoring - Change query String to string with place holder
    private final String getBookByIdQuery = "SELECT * FROM "+
            "aims.Book " +
            "INNER JOIN aims.Media " +
            "ON Media.id = Book.id " +
            "where Media.id = %s;";

	/**
	 * Clean code: one method do many things in many level of abstraction: get String query, get statement, get result, change to object
	 * --> separate to two more method: getBookByIdQuery(int id) to get String query, getBookResult() to change result from query
	 */
	
    // Data coupling
    @Override
    public Media getMediaById(int id) throws SQLException {
        // Clean Code: change sql to getBookByIdQuery,stm to bookStatement,res to bookResultSet
        String getBookByIdQuery = getBookByIdQuery(id);
        Statement bookStatement = AIMSDB.getInstance().getConnection().createStatement();
        ResultSet bookResultSet = bookStatement.executeQuery(getBookByIdQuery);
        if(bookResultSet.next()) {
        	return getBookResult(id, bookResultSet);
        } else {
            throw new SQLException();
        }
    }

    private String getBookByIdQuery(int id) {
    	return "SELECT * FROM "+
                "aims.Book " +
                "INNER JOIN aims.Media " +
                "ON Media.id = Book.id " +
                "where Media.id = " + id + ";";
    }
    
    private Book getBookResult(int id, ResultSet bookResultSet) throws SQLException {
    	// CleanCode : change title to mediaTitle, type to mediaType...
        String bookTitle = "";
        String bookType = bookResultSet.getString("type");
        int bookPrice = bookResultSet.getInt("price");
        String bookCategory = bookResultSet.getString("category");
        int bookQuantity = bookResultSet.getInt("quantity");

        // from Book table
        String bookAuthor = bookResultSet.getString("author");
        String bookCoverType = bookResultSet.getString("coverType");
        String bookPublisher = bookResultSet.getString("publisher");
        Date bookPublishDate = bookResultSet.getDate("publishDate");
        int bookNumOfPages = bookResultSet.getInt("numOfPages");
        String bookLanguage = bookResultSet.getString("language");
        String mediaBookCategory = bookResultSet.getString("bookCategory");

        return BookFactory.getInstance().createMedia(id, bookTitle, bookCategory, bookPrice, bookQuantity, bookType,
               bookAuthor, bookCoverType, bookPublisher, bookPublishDate, bookNumOfPages, bookLanguage, mediaBookCategory);
    }
}

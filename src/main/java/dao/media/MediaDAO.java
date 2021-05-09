package dao.media;

import entity.db.AIMSDB;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
// Singleton: MediaDAO quan ly cac products,va cac nghiep vu lien quan, do vay no len dc dat la Singleton
public class MediaDAO {
    // Clean Code: Method Refactoring - Change query string to constant
    private String getAllMediaQuery = "select * from Media";
    private String updateMediaFieldByIDQuery = " update Media set" + " %field "
            + "=" + " %value "
            + "where id=" + "%s;";
	
	/**
	 * Clean code: Source code to create new media is duplicated --> create new method: getMediaResult to return media
	 */

    // None Coupling
    // Clean Class: Divergent Change here: if media has a new field, we must get result in getAllMedia, getMediaById
    // Solution: create function getMediaResult and add to these above fucntion, and we have to change only getMediaResult
    public List getAllMedia() throws SQLException {
        // Clean Code: change stm to mediaStatement, res to mediaResultSet
        Statement mediaStatement = AIMSDB.getInstance().getConnection().createStatement();
        ResultSet mediaResultSet = mediaStatement.executeQuery(getAllMediaQuery);
        ArrayList mediaList = new ArrayList<>();
        while (mediaResultSet.next()) {
            Media media = getMediaResult(mediaResultSet);
            mediaList.add(media);
        }
        return mediaList;
    }

    // Data Coupling
    public Media getMediaById(int id) throws SQLException {

        // Clean Code: change sql to getMediaByIdQuery,stm to mediaStatement,res to MediaResultSet
        Statement mediaStatement = AIMSDB.getInstance().getConnection().createStatement();
        ResultSet MediaResultSet = mediaStatement.executeQuery(getAllMediaQuery);

        if (MediaResultSet.next()) {
            return getMediaResult(MediaResultSet);
        }
        return null;
    }

    // Control Coupling + Stamp Coupling
    // Clean Code: Method Refactoring - Remove tbname arguments because of none usage
    public void updateMediaFieldById(int id, String field, Object value) throws SQLException {
        // Clean Code: Change stm to updateMediaFieldByIdStatement
        Statement updateMediaFieldByIdStatement = AIMSDB.getInstance().getConnection().createStatement();
        // Control Coupling Here(if value is a string -> update value, else do nothing)
        if (value instanceof String){
            value = "\"" + value + "\"";
        }

        // Stamp coupling here(not use of tbname but still passing it)
        updateMediaFieldByIDQuery = String.format(updateMediaFieldByIDQuery,field,value,id);
        updateMediaFieldByIdStatement.executeUpdate(updateMediaFieldByIDQuery);
    }
    
    private Media getMediaResult(ResultSet MediaResultSet) throws SQLException {
    	return new Media(
                MediaResultSet.getInt("id"),
                MediaResultSet.getString("title"),
                MediaResultSet.getInt("quantity"),
                MediaResultSet.getString("category"),
                MediaResultSet.getString("imageUrl"),
                MediaResultSet.getInt("price"),
                MediaResultSet.getString("type"));
    }
}

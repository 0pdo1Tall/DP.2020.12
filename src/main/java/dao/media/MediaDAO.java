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
	
	/**
	 * Clean code: Source code to create new media is duplicated --> create new method: getMediaResult to return media
	 */
	
    // None Coupling
    public List getAllMedia() throws SQLException {
        // Clean Code: change stm to mediaStatement, res to mediaResultSet
        Statement mediaStatement = AIMSDB.getInstance().getConnection().createStatement();
        ResultSet mediaResultSet = mediaStatement.executeQuery("select * from Media");
        ArrayList medium = new ArrayList<>();
        while (mediaResultSet.next()) {
            medium.add(getMediaResult(mediaResultSet));
        }
        return medium;
    }

    // Data Coupling
    public Media getMediaById(int id) throws SQLException {

        // Clean Code: change sql to getMediaByIdQuery,stm to mediaStatement,res to MediaResultSet
        String getMediaByIdQuery = "SELECT * FROM Media ;";
        Statement mediaStatement = AIMSDB.getInstance().getConnection().createStatement();
        ResultSet MediaResultSet = mediaStatement.executeQuery(getMediaByIdQuery);

        if (MediaResultSet.next()) {
            return getMediaResult(MediaResultSet);
        }
        return null;
    }

    // Control Coupling + Stamp Coupling
    public void updateMediaFieldById(String tbname, int id, String field, Object value) throws SQLException {
        // Clean Code: Change stm to updateMediaFieldByIdStatement
        Statement updateMediaFieldByIdStatement = AIMSDB.getInstance().getConnection().createStatement();
        // Control Coupling Here(if value is a string -> update value, else do nothing)
        if (value instanceof String){
            value = "\"" + value + "\"";
        }

        // Stamp coupling here(not use of tbname but still passing it)
        updateMediaFieldByIdStatement.executeUpdate(" update Media set" + " "
                + field + "=" + value + " "
                + "where id=" + id + ";");
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

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

    // None Coupling
    public List getAllMedia() throws SQLException {
        // Clean Code: change stm to mediaStatement, res to mediaResultSet
        Statement mediaStatement = AIMSDB.getInstance().getConnection().createStatement();
        ResultSet mediaResultSet = mediaStatement.executeQuery("select * from Media");
        ArrayList medium = new ArrayList<>();
        while (mediaResultSet.next()) {
            Media media = new Media(
                    mediaResultSet.getInt("id"),
                    mediaResultSet.getString("title"),
                    mediaResultSet.getInt("quantity"),
                    mediaResultSet.getString("category"),
                    mediaResultSet.getString("imageUrl"),
                    mediaResultSet.getInt("price"),
                    mediaResultSet.getString("type"));
            medium.add(media);
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
            return new Media(
                    MediaResultSet.getInt("id"),
                    MediaResultSet.getString("title"),
                    MediaResultSet.getInt("quantity"),
                    MediaResultSet.getString("category"),
                    MediaResultSet.getString("imageUrl"),
                    MediaResultSet.getInt("price"),
                    MediaResultSet.getString("type"));
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
}

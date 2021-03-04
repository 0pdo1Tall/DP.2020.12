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
public class MediaDAO {

    // None Coupling
    public List getAllMedia() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from Media");
        ArrayList medium = new ArrayList<>();
        while (res.next()) {
            Media media = new Media(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getInt("quantity"),
                    res.getString("category"),
                    res.getString("imageUrl"),
                    res.getInt("price"),
                    res.getString("type"));
            medium.add(media);
        }
        return medium;
    }

    // Data Coupling
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM Media ;";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);

        if (res.next()) {
            return new Media(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getInt("quantity"),
                    res.getString("category"),
                    res.getString("imageUrl"),
                    res.getInt("price"),
                    res.getString("type"));
        }
        return null;
    }

    // Control Coupling + Stamp Coupling
    public void updateMediaFieldById(String tbname, int id, String field, Object value) throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        // Control Coupling Here(if value is a string -> update value, else do nothing)
        if (value instanceof String){
            value = "\"" + value + "\"";
        }

        // Stamp coupling here(not use of tbname but still passing it)
        stm.executeUpdate(" update Media set" + " "
                + field + "=" + value + " "
                + "where id=" + id + ";");
    }
}

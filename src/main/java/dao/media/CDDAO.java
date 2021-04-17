package dao.media;

import entity.db.AIMSDB;
import entity.media.CD;
import entity.media.CDFactory;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author
 */
public class CDDAO extends MediaDAO {

	/**
	 * Clean code: one method do many things in many level of abstraction: get String query, get statement, get result, change to object
	 * --> separate to two more method: getCDByIdQuery(int id) to get String query, getCDResult() to change result from query
	 */
	
    // Data Coupling
    @Override
    public Media getMediaById(int id) throws SQLException {
        // Clean Code: Change sql to getCDByIdQuery, res to CDResultSet
        String getCDByIdQuery = getCDByIdQuery(id);

        ResultSet CDResultSet = AIMSDB.getInstance().getConnection().createStatement().executeQuery(getCDByIdQuery);
        // Clean Code: change every tag to cd Tag
        if(CDResultSet.next()) {

        	return getCDResult(id, CDResultSet);

        } else {
            throw new SQLException();
        }
    }
    
    private String getCDByIdQuery(int id) {
    	return "SELECT * FROM "+
                "aims.CD " +
                "INNER JOIN aims.Media " +
                "ON Media.id = CD.id " +
                "where Media.id = " + id + ";";
    }
    
    private CD getCDResult(int id, ResultSet CDResultSet) throws SQLException {
    	// from media table
        String cdTitle = "";
        String cdType = CDResultSet.getString("type");
        int cdPrice = CDResultSet.getInt("price");
        String cdCategory = CDResultSet.getString("category");
        int cdQuantity = CDResultSet.getInt("quantity");

        // from CD table
        String cdArtist = CDResultSet.getString("artist");
        String cdRecordLabel = CDResultSet.getString("recordLabel");
        String cdMusicType = CDResultSet.getString("musicType");
        Date cdReleasedDate = CDResultSet.getDate("releasedDate");

        return CDFactory.getInstance().createMedia(id, cdTitle, cdCategory, cdPrice, cdQuantity, cdType,
                cdArtist, cdRecordLabel, cdMusicType, cdReleasedDate);
    }
}

package dao.media;

import entity.db.AIMSDB;
import entity.media.DVD;
import entity.media.DVDFactory;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author
 */
public class DVDDAO extends MediaDAO {
    // Clean Code: Method Refactoring - Change query String to string with place holder
    private String getDVDByIdQuery = "SELECT * FROM "+
            "aims.DVD " +
            "INNER JOIN aims.Media " +
            "ON Media.id = DVD.id " +
            "where Media.id = %s;";

	/**
	 * Clean code: one method do many things in many level of abstraction: get String query, get statement, get result, change to object
	 * --> separate to two more method: getDVDByIdQuery(int id) to get String query, getDVDResult() to change result from query
	 */
	
    // Data Coupling
    @Override
    public Media getMediaById(int id) throws SQLException {
        // Clean Code: change sql to getDVDByIdQuery, res to dvdResultSet
        ResultSet dvdResultSet = AIMSDB.getInstance().getConnection().createStatement().executeQuery(getDVDByIdQuery);
        // Clean Code: change every tag to dvdTag
        if (dvdResultSet.next()) {

            // from media table
            String dvdTitle = "";
            String dvdType = dvdResultSet.getString("type");
            int dvdPrice = dvdResultSet.getInt("price");
            String dvdCategory = dvdResultSet.getString("category");
            int dvdQuantity = dvdResultSet.getInt("quantity");
            String getDVDByIdQuery = getDVDByIdQuery(id);
            // Clean Code: change every tag to dvdTag
            if (dvdResultSet.next()) {
                return getDVDResult(id, dvdResultSet);
            } else {
                throw new SQLException();
            }
        }
        return null;
    }
    
    private String getDVDByIdQuery(int id) {
    	return "SELECT * FROM "+
                "aims.DVD " +
                "INNER JOIN aims.Media " +
                "ON Media.id = DVD.id " +
                "where Media.id = " + id + ";";
    }
    
    private DVD getDVDResult(int id, ResultSet dvdResultSet) throws SQLException {
    	 // from media table
        String dvdTitle = "";
        String dvdType = dvdResultSet.getString("type");
        int dvdPrice = dvdResultSet.getInt("price");
        String dvdCategory = dvdResultSet.getString("category");
        int dvdQuantity = dvdResultSet.getInt("quantity");

        // from DVD table
        String dvdDiscType = dvdResultSet.getString("discType");
        String dvdDirector = dvdResultSet.getString("director");
        int dvdRuntime = dvdResultSet.getInt("runtime");
        String dvdStudio = dvdResultSet.getString("studio");
        String dvdSubtitles = dvdResultSet.getString("subtitle");
        Date dvdReleasedDate = dvdResultSet.getDate("releasedDate");
        String dvdFilmType = dvdResultSet.getString("filmType");

        return DVDFactory.getInstance().createMedia(id, dvdTitle, dvdCategory, dvdPrice, dvdQuantity, dvdType, dvdDiscType, dvdDirector, dvdRuntime, dvdStudio, dvdSubtitles, dvdReleasedDate, dvdFilmType);

    }
}


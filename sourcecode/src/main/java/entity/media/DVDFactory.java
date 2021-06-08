package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class DVDFactory{

    private static DVDFactory dvdFactory;
    private DVDFactory(){}

    public static DVDFactory getInstance(){
        if(dvdFactory == null) dvdFactory = new DVDFactory();
        return dvdFactory;
    }

    public DVD createMedia(int id, String title, String category, int price, int quantity, String type, String discType,
                      String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType) throws SQLException {
        return new DVD(id,title,category,price,quantity,type,discType,director,runtime,studio,subtitles,releasedDate,filmType);
    }
}

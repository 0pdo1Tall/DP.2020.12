package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class CDFactory {

    private static CDFactory cdFactory;
    private CDFactory(){}

    public static CDFactory getInstance(){
        if(cdFactory == null) cdFactory = new CDFactory();
        return cdFactory;
    }

    public CD createMedia(int id, String title, String category, int price, int quantity, String type, String artist,
                      String recordLabel, String musicType, Date releasedDate) throws SQLException {
        return new CD(id,title,category,price,quantity,type,artist,recordLabel,musicType,releasedDate);
    }
}

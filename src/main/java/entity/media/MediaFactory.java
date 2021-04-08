package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class MediaFactory {

    public Media createMedia(int id, String title, String category, int i, int price, String s, String author, String coverType, String publisher, Date publishDate, int quantity, String language, String type) throws SQLException {
        return new Media(id,title,category,price,quantity,type);
    }

    // Method on Media here
}

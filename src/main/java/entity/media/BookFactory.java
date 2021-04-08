package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class BookFactory extends MediaFactory {

    private static BookFactory bookFactory;
    private BookFactory(){};
    public static BookFactory getInstance(){
        if(bookFactory == null) bookFactory = new BookFactory();
        return bookFactory;
    }

    public Book createMedia(int id, String title, String category, int price, int quantity, String type, String author,
                      String coverType, String publisher, Date publishDate, int numOfPages, String language,
                      String bookCategory) throws SQLException{
        return new Book(id,title,category,price,quantity,type,author,
                coverType,publisher,publishDate,numOfPages,language,
                bookCategory);
    }
}

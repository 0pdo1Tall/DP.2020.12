package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * @author nguyenlm Contains helper functions
 */

/*
 * Functional conhesion, vì không rơi vào những loại khác
 */

 // vi Utils chi la duy nhat 
public class Utils {
	// Clean Code: change INSTANCE to utilsInstance;
	public static Utils utilsInstance;
	private Utils() { }

    public static synchronized Utils getInstance(){
		if(utilsInstance == null){
			utilsInstance = new Utils();
		}
		return utilsInstance;
	}	
	
	public static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static final Logger LOGGER = getLogger(Utils.class.getName());
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}
}
package utils;

import common.exception.InvalidCardException;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

public class Date {
// Coincidental cohension. Nên tách riêng ra class riêng
	// SOLID: SRP ko lien quan den chuc nang cua Payment Controller
	
	/*
	 * Clean code: Cần thay đổi strs => dateSplit
	 */
	public static String getExpirationDate(String date) throws InvalidCardException {

		// Clean Code: change strs to dateString
		String[] dateString = date.split("/");
		if (dateString.length != 2) {
			throw new InvalidCardException();
		}

		String expirationDate = null;
		int month = -1;
		int year = -1;

		try {
			month = Integer.parseInt(dateString[0]);
			year = Integer.parseInt(dateString[1]);
			
			/**
			 * Clean Code: Method Refactoring - Data-Level Refactoring --> Introduce an intermediate variable
			 * Create intermediate variable month_invalid and year_invalid to increase readability
			 */
			boolean month_invalid = (month < 1) || (month > 12);
			boolean year_invalid = (year < Calendar.getInstance().get(Calendar.YEAR) % 100) || (year > 100);
			
			if (month_invalid || year_invalid) {
				throw new InvalidCardException();
			}
			expirationDate = dateString[0] + dateString[1];
		} catch (Exception ex) {
			throw new InvalidCardException();
		}

		return expirationDate;
		
		// Data coupling, chỉ sử dụng dữ liệu vừa đủ để xử lý kết quả
	}
}
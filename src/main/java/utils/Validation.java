package utils;

import common.exception.InvalidDeliveryInfoException;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
  public static void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
    if (validatePhoneNumber(info.get("phone"))
    || validateName(info.get("name"))
    || validateAddress(info.get("address"))) return;
    else throw new InvalidDeliveryInfoException();
  
    //stamp coupling: do info có nhiều hơn 3 trường dl mà trong hàm chỉ sử dụng 3 trường name, phone, address
  }
    
  public static boolean validatePhoneNumber(String phoneNumber) {
      // Clean Code: Use Constant PHONE_LENGTH = 10 instead
      if (phoneNumber.length() != PHONE_LENGTH) return false;
      if (!phoneNumber.startsWith(String.valueOf(START_PHONE_NUMBER))) return false;
      try {
          Integer.parseInt(phoneNumber);
      } catch (NumberFormatException e) {
          return false;
      }
      return true;
      
      // Data coupling, sử dụng vừa đủ dữ liệu để xử lý
  }

  public static boolean validateName(String name) {
      if (Objects.isNull(name)) return false;
      // Clean Code: change patternString to namePatternString,pattern to namePattern and matcher to nameMatcher
      String namePatternString = NAME_PATTERN;
      Pattern namePattern = Pattern.compile(namePatternString);
      Matcher nameMatcher = namePattern.matcher(name);
      return nameMatcher.matches();
      
      // Data coupling, sử dụng vừa đủ dữ liệu để xử lý
  }

  public static boolean validateAddress(String address) {
      if (Objects.isNull(address)) return false;
      // Clean Code: change patternString to addressPatternString,pattern to addressPattern and matcher to addressMatcher
      String addressPatternString = ADDRESS_PATTERN;
      Pattern addressPattern = Pattern.compile(addressPatternString);
      Matcher addressMatcher = addressPattern.matcher(address);
      return addressMatcher.matches();
      
      // Data coupling, sử dụng vừa đủ dữ liệu để xử lý
  }
}
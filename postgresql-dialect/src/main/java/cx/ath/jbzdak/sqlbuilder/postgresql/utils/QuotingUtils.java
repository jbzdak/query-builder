/*
 * Copyright (c) 2011 for Jacek Bzdak
 *
 * This file is part of query builder.
 *
 * Query builder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Query builder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Query builder.  If not, see <http://www.gnu.org/licenses/>.
 */

package cx.ath.jbzdak.sqlbuilder.postgresql.utils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import cx.ath.jbzdak.sqlbuilder.InvalidParameterException;
import cx.ath.jbzdak.sqlbuilder.dialect.DefaultQuotingManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public class QuotingUtils {


   public static final Pattern NON_ASCII_CHARS = Pattern.compile("[^\\p{ASCII}]");

   private QuotingUtils() {}

//   public static String quoteSimple(CharSequence string){
//      return DefaultQuotingManager.quote(string, "'", "''");
//   }

   public static String quoteUnicode(CharSequence string, String unicodeQuoteChar, String stringQuoteChar){
      String uescape = "";
      if(!"\\".equals(unicodeQuoteChar)){
         uescape = " UESCAPE " + DefaultQuotingManager.quote(unicodeQuoteChar, stringQuoteChar, stringQuoteChar+stringQuoteChar);
      }
      return "U&" + DefaultQuotingManager.quote(quoteUnicodeContents(string, unicodeQuoteChar), stringQuoteChar, stringQuoteChar + stringQuoteChar) + uescape;
   }

   public static boolean containsNonAsciiChars(CharSequence sequence){

      Matcher matcher = NON_ASCII_CHARS.matcher(sequence);

      return matcher.find();

   }


   private static String quoteUnicodeContents(CharSequence string, String quoteChar){
      String quotedQuotedChar = Matcher.quoteReplacement(quoteChar);
      String result = string.toString().replaceAll(Pattern.quote(quoteChar), quotedQuotedChar + quotedQuotedChar);
      Matcher matcher = NON_ASCII_CHARS.matcher(result);
      StringBuffer stringBuilder = new StringBuffer();
      while (matcher.find()){
         matcher.appendReplacement(stringBuilder,
                 Matcher.quoteReplacement(quoteSingleUnicodeChar(matcher.group(0), quoteChar)));
      }
      matcher.appendTail(stringBuilder);
      return stringBuilder.toString();
   }

   private static String quoteSingleUnicodeChar(String s, String quoteChar){
      if(s.length()!=1){
         throw new InvalidParameterException();
      }

      char unicode = s.charAt(0);

      String hex = Integer.toHexString(unicode);

      if(hex.length() > 4){
         hex = "+" + concat("0", 6 - hex.length()) + hex;
      }else{
         hex = concat("0", 4 - hex.length()) + hex;
      }

      return quoteChar + hex;
   }

   private static String concat(String item, int times){
      if(times == 0){
         return "";
      }
      if(times == 1){
         return item;
      }
      StringBuilder stringBuilder = new StringBuilder();
      for(int ii = 0; ii < times; ii++){
         stringBuilder.append(item);

      }
      return stringBuilder.toString();
   }

}

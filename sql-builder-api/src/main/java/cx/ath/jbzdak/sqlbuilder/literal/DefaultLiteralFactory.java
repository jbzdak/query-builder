package cx.ath.jbzdak.sqlbuilder.literal;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;

import java.sql.Date;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultLiteralFactory implements LiteralFactory {

   public SQLLiteral<Integer> create(int value){
      return new SQLLiteral<Integer>(SQLLiteralType.INTEGER, value);
   }

   public SQLLiteral<Float> create(float value){
      return new SQLLiteral<Float>(SQLLiteralType.FLOATING_POINT, value);
   }

   public SQLLiteral<Double> create(double value){
      return new SQLLiteral<Double>(SQLLiteralType.DOUBLE, value);
   }

   /**
    * Creates literal of type {@link SQLLiteralType.DATETIME}.
    */
   public SQLLiteral<Date> create(Date date){
      return new SQLLiteral<Date>(SQLLiteralType.DATETIME, date);
   }

   public SQLLiteral<Date> createDatetime(Date date){
      return new SQLLiteral<Date>(SQLLiteralType.DATETIME, date);
   }

   public SQLLiteral<Date> createDate(Date date){
      return new SQLLiteral<Date>(SQLLiteralType.DATE, date);
   }

   public SQLLiteral<String> create(String s){
      return new SQLLiteral<String>(SQLLiteralType.STRING, s);
   }









}

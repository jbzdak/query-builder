package cx.ath.jbzdak.sqlbuilder.literal;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;

import java.sql.Date;

/**
 * Created by: Jacek Bzdak
 */
public interface LiteralFactory {

   SQLLiteral<Integer> create(int value);

   SQLLiteral<Float> create(float value);

   SQLLiteral<Double> create(double value);

   SQLLiteral<Integer> create(Integer value);

   SQLLiteral<Double> create(Number value);

   SQLLiteral<Date> create(Date date);

   SQLLiteral<Date> createDatetime(Date date);

   SQLLiteral<Date> createDate(Date date);

   SQLLiteral<String> create(String s);

   SQLLiteral create(String literalType, Object value);
}

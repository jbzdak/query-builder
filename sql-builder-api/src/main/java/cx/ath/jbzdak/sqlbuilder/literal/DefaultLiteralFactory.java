package cx.ath.jbzdak.sqlbuilder.literal;

import com.sun.org.apache.bcel.internal.generic.ObjectType;
import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.generic.NewInstanceFactory;
import cx.ath.jbzdak.sqlbuilder.parameter.BoundParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultLiteralFactory implements LiteralFactory {

   private Map<String, Factory<? extends SQLLiteral>> literals;

   public DefaultLiteralFactory() {
      literals = new HashMap<String, Factory<? extends SQLLiteral>>();
      addLiteralType(SQLLiteralType.INTEGER, IntegerLiteral.class);
      addLiteralType(SQLLiteralType.FLOATING_POINT, FloatLiteral.class);
      addLiteralType(SQLLiteralType.DOUBLE, DoubleLiteral.class);
      addLiteralType(SQLLiteralType.DATETIME, DatetimeLiteral.class);
      addLiteralType(SQLLiteralType.DATE, DateLiteral.class);
      addLiteralType(SQLLiteralType.STRING, StringLiteral.class);
      addLiteralType(SQLLiteralType.PARAMETER, ParameterLiteral.class);

   }

   private void addLiteralType(String type, Class<? extends SQLLiteral> literalTypeClass){
      literals.put(type, new NewInstanceFactory<SQLLiteral>(literalTypeClass));
   }

   public SQLLiteral create(String type, Object value){
      SQLLiteral lit = literals.get(type).create();
      lit.setLiteralValue(value);
      lit.setLiteralType(type);
      return lit;
   }


   public SQLLiteral<Integer> create(int value){
      return create(SQLLiteralType.INTEGER, value);
   }

   public SQLLiteral<Float> create(float value){
      return create(SQLLiteralType.FLOATING_POINT, value);
   }

   public SQLLiteral<Double> create(double value){
    return create(SQLLiteralType.DOUBLE, value);
   }

   /**
    * Creates literal of type {@link SQLLiteralType.DATETIME}.
    */
   public SQLLiteral<Date> create(Date value){
      return create(SQLLiteralType.DATETIME, value);
   }

   public SQLLiteral<Date> createDatetime(Date value){
      return create(SQLLiteralType.DATETIME, value);
   }

   public SQLLiteral<Date> createDate(Date value){
      return create(SQLLiteralType.DATE, value);
   }

   public SQLLiteral<String> create(String value){
      return create(SQLLiteralType.DATE, value);
   }

   public SQLLiteral<Parameter> create(Parameter parameter){
      return create(SQLLiteralType.DATE, parameter);
   }

   public SQLLiteral<Integer> create(Integer value) {
      return create(value.intValue());
   }

   public SQLLiteral<Double> create(Number value) {
      return create(value.doubleValue());
   }
}


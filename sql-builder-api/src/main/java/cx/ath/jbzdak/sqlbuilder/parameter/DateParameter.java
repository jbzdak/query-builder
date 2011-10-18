package cx.ath.jbzdak.sqlbuilder.parameter;

import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.DialectAware;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by: Jacek Bzdak
 */
public class DateParameter extends AbstractParameter<Date> implements DialectAware{

   Dialect dialect;

   public DateParameter(String name) {
      super(ParameterType.DATE, name);
   }

   @Override
   public Date fromObject(Object o) {
      if (o instanceof String) {
         return fromString((String) o);

      }
      return (Date) o;
   }

   @Override
   public void setDialect(Dialect dialect) {
      this.dialect = dialect;
   }

   @Override
   public Date fromString(String string) {
      return dialect.getDialectConfig().parseDate(string);
   }
}

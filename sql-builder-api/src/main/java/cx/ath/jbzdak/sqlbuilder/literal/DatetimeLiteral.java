package cx.ath.jbzdak.sqlbuilder.literal;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;

import java.util.Date;

/**
 * Created by: Jacek Bzdak
 */
public class DatetimeLiteral extends SQLLiteral<Date>{

   public DatetimeLiteral() {
   }

   public DatetimeLiteral(Date literalValue) {
      super(SQLLiteralType.DATETIME, literalValue);
   }
}

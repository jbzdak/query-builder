package cx.ath.jbzdak.sqlbuilder.literal;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;

import java.util.Date;

/**
 * Created by: Jacek Bzdak
 */
public class DateLiteral extends SQLLiteral<Date>{

   public DateLiteral(String literalType, Date literalValue) {
      super(SQLLiteralType.DATE, literalValue);
   }


}

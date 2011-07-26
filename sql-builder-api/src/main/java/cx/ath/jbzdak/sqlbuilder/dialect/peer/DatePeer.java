package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.SQLPeer;
import cx.ath.jbzdak.sqlbuilder.literal.DateLiteral;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by: Jacek Bzdak
 */
public class DatePeer extends AbstractPeer<DateLiteral>{

   public DatePeer() {
   }

   public StringBuilder toSQL() {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      return new StringBuilder(dialect.getStringQuote()  + dateFormat.format(parent.getLiteralValue()) + dialect.getStringQuote());
   }
}

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

      return new StringBuilder();
   }

   public void appendToInternal(StringBuilder stringBuilder) {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      stringBuilder.append(" ");
      stringBuilder.append(context.getDialect().getStringQuote());
      stringBuilder.append(dateFormat.format(parent.getLiteralValue()));
      stringBuilder.append(context.getDialect().getStringQuote());
      stringBuilder.append(" ");
   }
}

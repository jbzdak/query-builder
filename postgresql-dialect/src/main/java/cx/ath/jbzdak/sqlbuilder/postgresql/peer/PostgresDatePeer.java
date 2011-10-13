package cx.ath.jbzdak.sqlbuilder.postgresql.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.dialect.peer.AbstractPeer;
import cx.ath.jbzdak.sqlbuilder.literal.DateLiteral;

/**
 * Created by: Jacek Bzdak
 */
public class PostgresDatePeer extends AbstractPeer<DateLiteral>{
   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append(" date ");
      stringBuilder.append(renderingContext.quoteDate(parent.getLiteralValue()));
      stringBuilder.append(" ");
   }
}

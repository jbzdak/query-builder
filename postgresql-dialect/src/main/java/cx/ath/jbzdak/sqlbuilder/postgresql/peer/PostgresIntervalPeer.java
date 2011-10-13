package cx.ath.jbzdak.sqlbuilder.postgresql.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.dialect.peer.AbstractPeer;
import cx.ath.jbzdak.sqlbuilder.postgresql.literal.IntervalPart;
import cx.ath.jbzdak.sqlbuilder.postgresql.literal.PostgresIntervalLiteral;
import cx.ath.jbzdak.sqlbuilder.postgresql.literal.SQLInterval;
import org.joda.time.Duration;
import org.joda.time.Interval;

import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class PostgresIntervalPeer extends AbstractPeer<PostgresIntervalLiteral>{

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append(" interval");

      SQLInterval interval = parent.getLiteralValue();

      StringBuilder sb = new StringBuilder();

      for (Map.Entry<IntervalPart, Integer> entry : interval.getIntervalParts().entrySet()) {
         sb.append(" ");
         sb.append(entry.getValue());
         sb.append(" ");
         sb.append(entry.getKey().name().toLowerCase());
      }
      
      stringBuilder.append(renderingContext.quoteString(sb.toString()));
   }
}

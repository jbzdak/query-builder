package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.SQLLiteral;

/**
 * Created by: Jacek Bzdak
 */
public class UnquotedLiteralPeer extends AbstractPeer<SQLLiteral>{

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append(' ');
      stringBuilder.append(parent.getLiteralValue().toString());
      stringBuilder.append(' ');
   }
}

package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;

/**
 * Created by: Jacek Bzdak
 */
public class UnquotedLiteralPeer extends AbstractPeer<SQLLiteral>{

   public void appendTo(StringBuilder stringBuilder) {
      stringBuilder.append(' ');
      stringBuilder.append(parent.getLiteralValue().toString());
      stringBuilder.append(' ');
   }
}

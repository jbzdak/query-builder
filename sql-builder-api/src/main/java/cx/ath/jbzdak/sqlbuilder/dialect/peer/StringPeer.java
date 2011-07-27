package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;

/**
 * Created by: Jacek Bzdak
 */
public class StringPeer extends AbstractPeer<StringLiteral>{

   public void appendTo(StringBuilder stringBuilder) {
      stringBuilder.append(' ');
      stringBuilder.append(dialect.getStringQuote());
      stringBuilder.append(parent.getLiteralValue());
      stringBuilder.append(dialect.getStringQuote());
      stringBuilder.append(' ');
   }
}

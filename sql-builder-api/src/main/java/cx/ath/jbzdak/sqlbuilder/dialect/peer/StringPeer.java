package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;

/**
 * Created by: Jacek Bzdak
 */
public class StringPeer extends AbstractPeer<StringLiteral>{

   public StringBuilder toSQL() {
      return new StringBuilder(dialect.getStringQuote() + parent.getLiteralValue() + dialect.getStringQuote());
   }
}

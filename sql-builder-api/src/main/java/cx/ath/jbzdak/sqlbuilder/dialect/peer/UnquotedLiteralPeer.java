package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;

/**
 * Created by: Jacek Bzdak
 */
public class UnquotedLiteralPeer extends AbstractPeer<SQLLiteral>{

   public StringBuilder toSQL() {
      return new StringBuilder(parent.getLiteralValue().toString());
   }
}

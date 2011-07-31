package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;

/**
 * Created by: Jacek Bzdak
 */
public interface Dialect {

   SQLPeer getPeer(SQLFactory sqlFactory);

   String getStringQuote();

   String getIdentifierQuote();

   boolean identifierNeedsQuoting(String identifier);

   DialectConfig getDialectConfig();

   Select select();

   LiteralFactory getLiteralFactory();
}

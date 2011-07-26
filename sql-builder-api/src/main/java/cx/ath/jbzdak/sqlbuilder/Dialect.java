package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;

/**
 * Created by: Jacek Bzdak
 */
public interface Dialect {

   SQLPeer getPeer(SQLFactory sqlFactory);

   String getStringQuote();

   String getIdentifierQuote();

   boolean identifierNeedsQuoting(String identifier);

   DialectConfig getDialectConfig();
}
